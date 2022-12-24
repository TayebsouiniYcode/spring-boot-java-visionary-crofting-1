package com.youcode.visionarycrofting.rest;

import com.youcode.visionarycrofting.util.Message;
import com.youcode.visionarycrofting.entity.Product;
import com.youcode.visionarycrofting.entity.Provider;
import com.youcode.visionarycrofting.service.ProviderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/provider")
public class providerController {
    private final ProviderService providerService;

    public providerController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping("/providers")
    public List<Provider> getProviders()
    {
       return providerService.getProviders();
    }

    @PostMapping("/add")
    public void addProvider(@RequestBody Provider provider)
    {
        providerService.addProvider(provider);
    }

    @DeleteMapping("/delete/{providerId}")
    public Message deleteProvider( @PathVariable("providerId") Long providerId )
    {
        return providerService.deleteProvider(providerId);
    }

    @PutMapping("/update")
    @ResponseBody
    public Provider updateProvider(@RequestBody Provider provider)
    {
        providerService.updateProvider(provider);
        return provider;
    }

    @PutMapping ("/invoice/validate/{id}")
    public Optional < Product > validateInvoice( @PathVariable("id") Long id)
    {
        return providerService.validateInvoice(id);
    }
}
