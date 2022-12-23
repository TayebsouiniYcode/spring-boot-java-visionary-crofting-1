package com.youcode.visionarycrofting.controller;


import com.youcode.visionarycrofting.entity.Invoice;
import com.youcode.visionarycrofting.entity.Stock;
import com.youcode.visionarycrofting.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("/insert")
    public void addInvoice(@RequestBody Invoice invoice)
    {
        invoiceService.addInvoice(invoice);
    }

    @DeleteMapping("/delete/{invoiceId}")
    public void deleteInvoice(@PathVariable("invoiceId") Long invoiceId )
    {
        invoiceService.deleteInvoice(invoiceId);
    }

    @PutMapping("/update/{invoiceId}")
    public void updateProvider(@PathVariable("invoiceId") Long invoiceId, @RequestBody Invoice invoice )
    {
        invoiceService.updateInvoice(invoiceId , invoice);
    }
}
