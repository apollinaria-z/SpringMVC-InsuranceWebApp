package by.zolotaya.app.controllers;

import by.zolotaya.app.dao.impl.ClientDAO;
import by.zolotaya.app.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private ClientDAO clientDAO;

    @Autowired
    public ClientController(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @GetMapping()
    public String getAllClients(Model model){
        model.addAttribute("clients", clientDAO.getAllClients());
        return "clients/getAllClients";
    }

    @GetMapping("/{id}")
    public String getClientById(@PathVariable("id") int id, Model model){
        model.addAttribute("client", clientDAO.getClientById(id));
        return "clients/getClientById";
    }

    @GetMapping("/new")
    public String newClient(@ModelAttribute("client") Client client){

        return "clients/newClient";
    }

    @PostMapping()
    public String addClient(@ModelAttribute("client") @Valid Client client,
                            BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "clients/newClient";

        clientDAO.addClient(client);
        return "redirect:/clients";
    }

    @GetMapping("/{id}/edit")
    public String editClient(Model model, @PathVariable("id") int id){
        model.addAttribute("client", clientDAO.getClientById(id));
        return "clients/editClient";
    }

    @PatchMapping("/{id}")
    public String updateClient(@ModelAttribute("client") @Valid Client client,
                               BindingResult bindingResult, @PathVariable("id") int id){
        if (bindingResult.hasErrors())
            return "clients/editClient";
        clientDAO.updateClient(id, client);
        return"redirect:/clients";
    }

    @DeleteMapping("/{id}")
    public String deleteClient(@PathVariable("id") int id){
        clientDAO.deleteClient(id);
        return "redirect:/clients";
    }
}
