package com.youcode.visionarycrofting.controller;

import com.youcode.visionarycrofting.classes.Message;
import com.youcode.visionarycrofting.classes.PasserCommande;
import com.youcode.visionarycrofting.entity.Client;
import com.youcode.visionarycrofting.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(path = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Client> getClients() { return clientService.getClients();}

    @RequestMapping(path = "/get/{id}", method = RequestMethod.GET)
    public Optional<Client> getOne(@PathVariable("id") Long clientId){
        return clientService.getOneById(clientId);
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Client registerNewClient(@RequestBody Client client)
    {
        return clientService.addClient(client);
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
    public Message deleteClient( @PathVariable("id") Long clientId)
    { return clientService.deleteClient(clientId); }

    @RequestMapping(path = "/update", method = RequestMethod.PUT)
    public Client updateClient(@RequestBody Client client)

    {
        return clientService.updateClient(client);
    }

    @RequestMapping(path = "/order/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Client passerCommande(@PathVariable Long idClient,@RequestBody Collection<PasserCommande> productList)
    { return clientService.passerCommande(idClient, productList); }
}
