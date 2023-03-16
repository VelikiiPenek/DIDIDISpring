package com.example.Salon.models;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Sotrydniki {
    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty(message = "Поле не может быть пустым")
    private String surename;
    @NotEmpty(message = "Поле не может быть пустым")
    private String name;
    @NotEmpty(message = "Поле не может быть пустым")
    private String middlename;
    @NotEmpty(message = "Поле не может быть пустым")
    private String bornDate;
    @NotEmpty(message = "Поле не может быть пустым")
    private String adressEmployee;
    @NotEmpty(message = "Поле не может быть пустым")
    private String numberphone;

    @ManyToOne(optional = true,cascade = CascadeType.DETACH)
    private Doljnosti Doljnost;
    @ManyToOne(optional = true,cascade = CascadeType.DETACH)
    private Salon Salon;
    @OneToOne(mappedBy = "Sotrydniki",cascade = CascadeType.DETACH)
    private Zapisi zapisi;

    public Sotrydniki() {
    }

    public Sotrydniki(String surename, String name, String middlename, String bornDate, String adressEmployee, String numberphone, Doljnosti doljnost, com.example.Salon.models.User user, com.example.Salon.models.Salon salon) {
        this.surename = surename;
        this.name = name;
        this.middlename = middlename;
        this.bornDate = bornDate;
        this.adressEmployee = adressEmployee;
        this.numberphone = numberphone;
        Doljnost = doljnost;
        Salon = salon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getBornDate() {
        return bornDate;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }

    public String getAdressEmployee() {
        return adressEmployee;
    }

    public void setAdressEmployee(String adressEmployee) {
        this.adressEmployee = adressEmployee;
    }

    public String getNumberphone() {
        return numberphone;
    }

    public void setNumberphone(String numberphone) {
        this.numberphone = numberphone;
    }

    public Doljnosti getDoljnost() {
        return Doljnost;
    }

    public void setDoljnost(Doljnosti doljnost) {
        Doljnost = doljnost;
    }

    public com.example.Salon.models.Salon getSalon() {
        return Salon;
    }

    public void setSalon(com.example.Salon.models.Salon salon) {
        Salon = salon;
    }

    public Zapisi getZapisi() {
        return zapisi;
    }

    public void setZapisi(Zapisi zapisi) {
        this.zapisi = zapisi;
    }

}
