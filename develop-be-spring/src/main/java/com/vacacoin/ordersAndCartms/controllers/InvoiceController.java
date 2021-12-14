package com.vacacoin.ordersAndCartms.controllers;
import com.vacacoin.ordersAndCartms.exceptions.InvoiceNotFoundException;
import com.vacacoin.ordersAndCartms.models.Invoice;
import com.vacacoin.ordersAndCartms.repositories.InvoiceRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class InvoiceController {
    private final InvoiceRepository invoiceRepository;

    public InvoiceController(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @PostMapping("/invoices")
    Invoice newInvoice(@RequestBody Invoice invoice){
        /*
         Asigna el número de factura buscando el último registrado + 1
         */
        invoice.setNumInvoice(getLastInvoice()+1);
        invoice.setDate(LocalDateTime.now());
        /*
        Asigna el valor total de la facura, multiplicando el valor unitario y la cantidad de
        unidades adquiridas.
         */
        invoice.setValueTotal(invoice.getValueUnit()*invoice.getQuantity());
        return invoiceRepository.save(invoice);
    }

    @GetMapping("/invoices/{numInvoice}")
    Invoice getInvoice(@PathVariable Integer numInvoice){
        return invoiceRepository.findById(numInvoice)
                .orElseThrow(()-> new InvoiceNotFoundException("No se encuentra la factura"));
    }

    @DeleteMapping("/invoices/{numInvoice}")
    void deleteInvoice(@PathVariable Integer numInvoice){
        invoiceRepository.deleteById(numInvoice);
    }

    @PutMapping("/invoices/{numInvoice}")
    Invoice updateInvoice(@PathVariable Integer numInvoice, @RequestBody Invoice new_invoice){
        Invoice old_invoice = invoiceRepository.findById(numInvoice).orElse(null);
        old_invoice.setDate(LocalDateTime.now());
        old_invoice.setProject(new_invoice.getProject());
        old_invoice.setUserCreator(new_invoice.getUserCreator());
        old_invoice.setUserCostumer(new_invoice.getUserCostumer());
        old_invoice.setValueUnit(new_invoice.getValueUnit());
        old_invoice.setQuantity(new_invoice.getQuantity());
        old_invoice.setValueTotal(new_invoice.getValueUnit()*new_invoice.getQuantity());
        return invoiceRepository.save(old_invoice);
    }

    @GetMapping("/invoices")
    List<Invoice> getAllInvoice(){
        return invoiceRepository.findAll();
    }

    @GetMapping("/invoices/ultimate")
    Integer getLastInvoice(){
        int numLastInvoice;
        try {
            numLastInvoice = (getAllInvoice().get(getAllInvoice().size()-1).getNumInvoice());
        } catch (Exception e) {
            numLastInvoice = 0;
        }
        return numLastInvoice;
    }
}
