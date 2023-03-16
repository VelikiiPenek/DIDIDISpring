package com.example.Salon.models;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
public class Klient {
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
    private String numberphone;

    @OneToMany(mappedBy = "Klient",fetch = FetchType.EAGER)
    private Collection<Zapisi> zapisiCollection;

    public Klient() {
    }

    public Klient(String surename, String name, String middlename, String numberphone, com.example.Salon.models.User user) {
        this.surename = surename;
        this.name = name;
        this.middlename = middlename;
        this.numberphone = numberphone;
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

    public String getNumberphone() {
        return numberphone;
    }

    public void setNumberphone(String numberphone) {
        this.numberphone = numberphone;
    }
}
