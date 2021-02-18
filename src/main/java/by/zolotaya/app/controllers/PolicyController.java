package by.zolotaya.app.controllers;

import by.zolotaya.app.dao.impl.ClientDAO;
import by.zolotaya.app.dao.impl.PolicyDAO;
import by.zolotaya.app.models.Client;
import by.zolotaya.app.models.Coverage;
import by.zolotaya.app.models.Policy;
import by.zolotaya.app.models.PolicyRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/policies")
public class PolicyController {

    private PolicyDAO policyDAO;
    private ClientDAO clientDAO;

    @Autowired
    public PolicyController(PolicyDAO policyDAO, ClientDAO clientDAO) {
        this.policyDAO = policyDAO;
        this.clientDAO = clientDAO;
    }

    @GetMapping()
    public String getAllPolicies(Model model) {
        model.addAttribute("policies", policyDAO.getAllPolicies());
        return "policies/getAllPolicies";
    }

    @GetMapping("/{id}")
    public String getPolicyById(@PathVariable("id") int id, Model model) {
        model.addAttribute("policy", policyDAO.getPolicyById(id));
        return "policies/getPolicyById";
    }

    @GetMapping("/new")
    public String newPolicy(@ModelAttribute("policyreq") PolicyRequestModel policyreq) {
        return "policies/newPolicy";
    }

    @PostMapping("/save")
    public String addPolicy(@ModelAttribute("policyreq") @Valid PolicyRequestModel policyreq,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "policies/newPolicy";

        Policy policy = new Policy();
        Client client = new Client();
        if(clientDAO.getClientById(policyreq.getClientid()).getSurname() != null) {
            client = clientDAO.getClientById(policyreq.getClientid());
            policy.setClient(client);
            policy.setCoverage(Coverage.valueOf(policyreq.getCoverage()));
            policy.setPrice(policyreq.getPrice());
            policy.setProperty(policyreq.getProperty());
            policyDAO.createPolicy(policy);
            return "redirect:/policies";
        } else return "redirect:/clients";
    }

    @GetMapping("/{id}/edit")
    public String editPolicy(Model model, @PathVariable("id") int id) {
        model.addAttribute("policy", policyDAO.getPolicyById(id));
        return "policies/editPolicy";
    }

    @PatchMapping("/{id}")
    public String updatePolicy(@ModelAttribute("policy") @Valid Policy policy,
                               BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "policies/editPolicy";
        policyDAO.updatePolicy(id, policy);
        return "redirect:/policies";
    }

    @DeleteMapping("/{id}")
    public String deletePolicy(@PathVariable("id") int id) {
        policyDAO.deletePolicy(id);
        return "redirect:/policies";
    }

}
