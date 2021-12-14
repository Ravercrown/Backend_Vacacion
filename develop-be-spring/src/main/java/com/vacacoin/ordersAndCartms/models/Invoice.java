package com.vacacoin.ordersAndCartms.models;
import org.springframework.data.annotation.Id;


import java.time.LocalDateTime;


public class Invoice {
    @Id
    private int numInvoice;
    private LocalDateTime date;
    private String project;
    private String userCreator;
    private String userCostumer;
    private Float valueUnit;
    private int quantity;
    private Float valueTotal;

    public Invoice(int numInvoice, LocalDateTime date, String project, String userCreator, String userCostumer, Float valueUnit, int quantity, Float valueTotal) {
        this.numInvoice = numInvoice;
        this.date = date;
        this.project = project;
        this.userCreator = userCreator;
        this.userCostumer = userCostumer;
        this.valueUnit = valueUnit;
        this.quantity = quantity;
        this.valueTotal = valueTotal;
    }

    public int getNumInvoice() {
        return numInvoice;
    }

    public void setNumInvoice(int numInvoice) {
        this.numInvoice = numInvoice;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getUserCreator() {
        return userCreator;
    }

    public void setUserCreator(String userCreator) {
        this.userCreator = userCreator;
    }

    public String getUserCostumer() {
        return userCostumer;
    }

    public void setUserCostumer(String userCostumer) {
        this.userCostumer = userCostumer;
    }

    public Float getValueUnit() {
        return valueUnit;
    }

    public void setValueUnit(Float valueUnit) {
        this.valueUnit = valueUnit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Float getValueTotal() {
        return valueTotal;
    }

    public void setValueTotal(Float valueTotal) {
        this.valueTotal = valueTotal;
    }
}
