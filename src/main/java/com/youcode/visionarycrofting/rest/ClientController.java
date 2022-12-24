package com.youcode.visionarycrofting.rest;

import com.youcode.visionarycrofting.util.Message;
import com.youcode.visionarycrofting.util.PasserCommande;
import com.youcode.visionarycrofting.entity.Client;
import com.youcode.visionarycrofting.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/clients")
    @ResponseBody
    public ResponseEntity<List<Client>> getClients() {
        return ResponseEntity.ok ().body ( clientService.getClients() );
    }

    @GetMapping("/client/{id}")
    public Optional<Client> getOne(@PathVariable("id") Long clientId){
        return clientService.getOneById(clientId);
    }

    @PostMapping("/add")
    public Client registerNewClient(@RequestBody Client client)
    {
        return clientService.addClient(client);
    }

    @DeleteMapping("/delete/{id}")
    public Message deleteClient( @PathVariable("id") Long clientId)
    { return clientService.deleteClient(clientId); }

    @PutMapping("/update")
    public Client updateClient(@RequestBody Client client)

    {
        return clientService.updateClient(client);
    }

    @PostMapping("/passercommand/{id}")
    @ResponseBody
    public Client passerCommande(@PathVariable Long idClient,@RequestBody Collection<PasserCommande> productList)
    { return clientService.passerCommande(idClient, productList); }
}
