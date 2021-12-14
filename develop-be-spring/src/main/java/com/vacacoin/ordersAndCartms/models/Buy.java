package com.vacacoin.ordersAndCartms.models;

import org.springframework.data.annotation.Id;

public class Buy {
    @Id
    private int numBuy;
    private int numInvoice;
    private Invoice invoice;
    private Float valuePurchase;
    private String paymentMethod;

    public Buy(int numBuy, int numInvoice, Invoice invoice, Float valuePurchase, String paymentMethod) {
        this.numBuy = numBuy;
        this.numInvoice = numInvoice;
        this.invoice = invoice;
        this.valuePurchase = valuePurchase;
        this.paymentMethod = paymentMethod;
    }

    public int getNumBuy() {
        return numBuy;
    }

    public void setNumBuy(int numBuy) {
        this.numBuy = numBuy;
    }

    public int getNumInvoice() {
        return numInvoice;
    }

    public void setNumInvoice(int numInvoice) {
        this.numInvoice = numInvoice;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Float getValuePurchase() {
        return valuePurchase;
    }

    public void setValuePurchase(Float valuePurchase) {
        this.valuePurchase = valuePurchase;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
