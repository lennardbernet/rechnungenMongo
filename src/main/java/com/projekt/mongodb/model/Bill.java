package com.projekt.mongodb.model;

import com.projekt.mongodb.model.Adress;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Bill{
    @Id
    private String billId;

    private String firstname;
    private String lastname;
    private int amount;
    private String iban;
    private Date date;
    private Adress adress;

    public Bill(){
    }

    public Bill(String firstname, String lastname, int amount, String iban, Date date, Adress adress){
        this.firstname = firstname;
        this.lastname = lastname;
        this.amount = amount;
        this.iban = iban;
        this.date = date;
        this.adress = adress;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String rechnungId) {
        this.billId = rechnungId;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }
}
