package by.zolotaya.app.controllers;

import by.zolotaya.app.dao.impl.PolicyDAO;
import by.zolotaya.app.models.Client;
import by.zolotaya.app.models.Policy;
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

    @Autowired
    public PolicyController(PolicyDAO policyDAO){
        this.policyDAO = policyDAO;
    }

    @GetMapping()
    public String getAllPolicies(Model model){
        model.addAttribute("policies", policyDAO.getAllPolicies());
        return "policies/getAllPolicies";
    }

    @GetMapping("/{id}")
    public String getPolicyById(@PathVariable("id") int id, Model model){
        model.addAttribute("policy", policyDAO.getPolicyById(id));
        return "policies/getPolicyById";
    }

    @GetMapping("/new")
    public String newPolicy(@ModelAttribute("policy") Policy policy){
        return "policies/newPolicy";
    }

    @PostMapping()
    public String addPolicy(@ModelAttribute("policy") @Valid Policy policy,
                            BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "policies/newPolicy";

        policyDAO.createPolicy(policy);
        return "redirect:/policies";
    }

    @GetMapping("/{id}/edit")
    public String editPolicy(Model model, @PathVariable("id") int id){
        model.addAttribute("policy", policyDAO.getPolicyById(id));
        return "policies/editPolicy";
    }

    @PatchMapping("/{id}")
    public String updatePolicy(@ModelAttribute("policy") @Valid Policy policy,
                               BindingResult bindingResult, @PathVariable("id") int id){
        if (bindingResult.hasErrors())
            return "policies/editPolicy";
        policyDAO.updatePolicy(id, policy);
        return"redirect:/policies";
    }

    @DeleteMapping("/{id}")
    public String deletePolicy(@PathVariable("id") int id){
        policyDAO.deletePolicy(id);
        return "redirect:/policies";
    }

}
