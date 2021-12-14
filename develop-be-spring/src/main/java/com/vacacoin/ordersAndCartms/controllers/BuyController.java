package com.vacacoin.ordersAndCartms.controllers;

import com.vacacoin.ordersAndCartms.exceptions.BuyNotFoundException;
import com.vacacoin.ordersAndCartms.exceptions.InvoiceNotFoundException;
import com.vacacoin.ordersAndCartms.exceptions.PaymentMethodNotFoundException;
import com.vacacoin.ordersAndCartms.models.Buy;
import com.vacacoin.ordersAndCartms.models.Invoice;
import com.vacacoin.ordersAndCartms.repositories.BuyRepository;
import com.vacacoin.ordersAndCartms.repositories.InvoiceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BuyController {
    private final BuyRepository buyRepository;
    private final InvoiceRepository invoiceRepository;

    public BuyController(BuyRepository buyRepository, InvoiceRepository invoiceRepository){
        this.buyRepository = buyRepository;
        this.invoiceRepository = invoiceRepository;
    }

    @PostMapping("/buys")
    Buy newBuy(@RequestBody Buy buy){
        buy.setNumBuy(getLastBuy()+1);
        Invoice invoiceSearch = invoiceRepository.findById(buy.getNumInvoice()).orElse(null);
        if (invoiceSearch == null){
            throw new InvoiceNotFoundException("No se encuentra una factura con ese número");
        }
        buy.setValuePurchase(invoiceSearch.getValueTotal());
        if (!buy.getPaymentMethod().equals("Credit card") && !buy.getPaymentMethod().equals("Debit card") &&
            !buy.getPaymentMethod().equals("PSE") && !buy.getPaymentMethod().equals("Send to bank")){
            throw new PaymentMethodNotFoundException("Método de pago no valido");
        }
        buy.setInvoice(invoiceSearch);
        return buyRepository.save(buy);
    }

    @GetMapping("/buys/{numBuy}")
    Buy getBuy(@PathVariable Integer numBuy){
        return buyRepository.findById(numBuy)
                .orElseThrow(()-> new BuyNotFoundException("No se encuentra ninguna compra con ese número"));
    }

    @DeleteMapping("/buys/{numBuy}")
    void deleteBuy(@PathVariable Integer numBuy){
        buyRepository.deleteById(numBuy);
    }

    @PutMapping("/buys/{numBuy}")
    Buy updateBuy(@PathVariable Integer numBuy, @RequestBody Buy new_buy){
        Invoice invoiceSearch = invoiceRepository.findById(new_buy.getNumInvoice()).orElse(null);
        if (invoiceSearch == null){
            throw new InvoiceNotFoundException("No se encuentra una factura con ese número");
        }
        Buy old_buy = buyRepository.findById(numBuy).orElse(null);
        old_buy.setNumInvoice(new_buy.getNumInvoice());
        old_buy.setInvoice(invoiceSearch);
        old_buy.setValuePurchase(invoiceSearch.getValueTotal());
        old_buy.setPaymentMethod(new_buy.getPaymentMethod());
        return buyRepository.save(old_buy);
    }


    @GetMapping("/buys")
    List<Buy> getAllBuy(){
        return buyRepository.findAll();
    }

    @GetMapping("/buys/ultimate")
    Integer getLastBuy(){
        int numLastBuy;
        try {
            numLastBuy = (getAllBuy().get(getAllBuy().size()-1).getNumBuy());
        } catch (Exception e) {
            numLastBuy = 0;
        }
        return numLastBuy;
    }
}
