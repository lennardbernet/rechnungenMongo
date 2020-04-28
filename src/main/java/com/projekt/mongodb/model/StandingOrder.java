package com.projekt.mongodb.model;

import com.projekt.mongodb.model.Adress;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class StandingOrder {

    @Id
    private String standingOrderId;

    private String firstname;
    private String lastname;
    private String iban;
    private int amount;
    private int executions;
    private String repetitionrate;
    private Adress adress;

    public StandingOrder(String firstname, String lastname, String iban, int amount, int executions, String repetitionrate, Adress adress) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.iban = iban;
        this.amount = amount;
        this.executions = executions;
        this.repetitionrate = repetitionrate;
        this.adress = adress;
    }

    public String getStandingOrderId() {
        return standingOrderId;
    }

    public void setStandingOrderId(String standingOrderId) {
        this.standingOrderId = standingOrderId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getExecutions() {
        return executions;
    }

    public void setExecutions(int executions) {
        this.executions = executions;
    }

    public String getRepetitionrate() {
        return repetitionrate;
    }

    public void setRepetitionrate(String repetitionrate) {
        this.repetitionrate = repetitionrate;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }
}
