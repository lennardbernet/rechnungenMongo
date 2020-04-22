package com.projekt.rechnungen.model;

import javax.persistence.*;

@Entity
@Table(name = "standingorder")
public class StandingOrder {

    @Id
    @Column(name = "standingorder_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dauerauftragId;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "iban")
    private String iban;

    @Column(name = "amount")
    private int amount;

    @Column(name = "executions")
    private int executions;

    @Column(name = "repetitionrate")
    private String repetitionrate;

    @ManyToOne
    @JoinColumn(name = "adress_idfk", referencedColumnName = "adress_id")
    private Adress adress;

    public StandingOrder(){

    }
    public StandingOrder(String firstname, String lastname, String iban, int amount, int executions, String repetitionrate, Adress adress){
        this.firstname = firstname;
        this.lastname = lastname;
        this.iban = iban;
        this.amount = amount;
        this.executions = executions;
        this.repetitionrate = repetitionrate;
        this.adress = adress;
    }

    public int getDauerauftragId() {
        return dauerauftragId;
    }

    public void setDauerauftragId(int dauerauftragId) {
        this.dauerauftragId = dauerauftragId;
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

    @Override
    public String toString() {
        return "StandingOrder{" +
                "dauerauftragId=" + dauerauftragId +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", iban=" + iban +
                ", amount=" + amount +
                ", executions=" + executions +
                ", repetitionrate='" + repetitionrate + '\'' +
                ", adress=" + adress +
                '}';
    }
}
