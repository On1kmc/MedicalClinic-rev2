package com.ivanov.MedicalClinic.controller;

import com.ivanov.MedicalClinic.dto.ClientDTO;
import com.ivanov.MedicalClinic.dto.OrderDTO;
import com.ivanov.MedicalClinic.model.Client;
import com.ivanov.MedicalClinic.services.ClientService;
import com.ivanov.MedicalClinic.services.OrderService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/owner")
public class ClientController {

    private final ClientService clientService;



    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String owners(Model model) {
        List<Client> clientList = clientService.loadClients();
        List<ClientDTO> clientDTOList = clientService.toClientDTOList(clientList);
        model.addAttribute("clients", clientDTOList);
        return "owners";
    }


    @GetMapping("/new")
    public String newOwner(@ModelAttribute("client") ClientDTO clientDTO) {
        return "new-owner";
    }

    @PostMapping("/new")
    public String addOwner(@ModelAttribute("client") @Valid ClientDTO clientDTO, BindingResult bindingResult) {
        Client client = clientService.toClient(clientDTO);
        if (bindingResult.hasErrors()) {
            return "new-owner";
        }
        clientService.saveOwner(client);
        return "redirect:/owner";
    }

    @GetMapping("/{id}")
    public String ownerPage(@PathVariable("id") int id, Model model) {
        Client cl = clientService.findClientById(id);
        ClientDTO client = clientService.toClientDTO(cl);
        model.addAttribute("client", client);
        model.addAttribute("orderList", cl.getOrderList());
        return "owner-page";
    }

}
