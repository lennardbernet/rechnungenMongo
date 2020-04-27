package com.projekt.mysql.model;


import javax.persistence.*;

@Entity
@Table(name="adress")
public class Adress {

    @Id
    @Column(name="adress_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int adressId;

    @Column(name="street")
    private String street;

    @Column(name="streetnumber")
    private int streetnumber;

    @Column(name="zip_code")
    private int zipCode;

    @Column(name="place")
    private String place;

    public Adress(){
    }

    public Adress(int adressId){
        this.adressId = adressId;
    }

    public Adress(String street, int streetnumber, int zipCode, String place) {
        this.street = street;
        this.streetnumber = streetnumber;
        this.zipCode = zipCode;
        this.place = place;
    }

    public int getAdressId() {
        return adressId;
    }

    public void setAdressId(int adressId) {
        this.adressId = adressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetnumber() {
        return streetnumber;
    }

    public void setStreetnumber(int streetnumber) {
        this.streetnumber = streetnumber;
    }

    public int getZip_code() {
        return zipCode;
    }

    public void setZip_code(int zip_code) {
        this.zipCode = zip_code;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "Adress{" +
                "adressId=" + adressId +
                ", street='" + street + '\'' +
                ", streetnumber=" + streetnumber +
                ", zipCode=" + zipCode +
                ", place='" + place + '\'' +
                '}';
    }
}
