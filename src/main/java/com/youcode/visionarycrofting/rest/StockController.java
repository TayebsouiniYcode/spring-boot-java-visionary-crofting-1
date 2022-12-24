package com.youcode.visionarycrofting.rest;

import com.youcode.visionarycrofting.util.AppelDoffre;
import com.youcode.visionarycrofting.util.Message;
import com.youcode.visionarycrofting.entity.Invoice;
import com.youcode.visionarycrofting.entity.Product;
import com.youcode.visionarycrofting.entity.Stock;
import com.youcode.visionarycrofting.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/stock")
public class StockController {
    @Autowired
    StockService stockService;

    @GetMapping("/stocks")
    @ResponseBody
    public List<Stock> findAll(){
        return stockService.findAll();
    }

    @PostMapping(path = "/insert")
    @ResponseBody
    public Stock insertStock(@RequestBody Stock stock){
         return stockService.insertStock(stock);
    }

    @DeleteMapping(path = "/delete/{stockid}")
    @ResponseBody
    public Message deleteStockById( @PathVariable("stockid") Long id){
        return stockService.deleteStockById(id);
    }

    @PutMapping(path = "/update/{stockid}")
    @ResponseBody
    public Stock updateStock(
            @PathVariable("stockid") Long id , @RequestBody Stock stock){
       return stockService.updateStock(id , stock);
    }

    @PostMapping(path = "/invoide/add")
    @ResponseBody
    public Invoice addInvoice(@RequestBody AppelDoffre appelDoffre){
        return  stockService.addAppelDoffre(appelDoffre);
    }

    @PostMapping("/add/product/{id}")
    @ResponseBody
    public Stock addProduct(@PathVariable Long id, @RequestBody Product product){
        return stockService.addProduct(id, product);
    }
}
