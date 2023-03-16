package com.example.Salon.models;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Zapisi {
    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty(message = "Поле не может быть пустым")
    private String dataZapisi;
    @NotEmpty(message = "Поле не может быть пустым")
    private String vremyaNachala;
    @NotEmpty(message = "Поле не может быть пустым")
    private String visit;
    @ManyToOne(optional = true,cascade = CascadeType.DETACH)
    private Klient Klient;
    @ManyToOne(optional = true,cascade = CascadeType.DETACH)
    private Yslygi Yslygi;
    @OneToOne(optional = true,cascade = CascadeType.DETACH)
    private Sotrydniki Sotrydniki;

    public Zapisi() {
    }

    public Zapisi(String dataZapisi, String vremyaNachala, String visit, com.example.Salon.models.Klient klient, com.example.Salon.models.Yslygi yslygi, com.example.Salon.models.Sotrydniki sotrydniki) {
        this.dataZapisi = dataZapisi;
        this.vremyaNachala = vremyaNachala;
        this.visit = visit;
        Klient = klient;
        Yslygi = yslygi;
        Sotrydniki = sotrydniki;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataZapisi() {
        return dataZapisi;
    }

    public void setDataZapisi(String dataZapisi) {
        this.dataZapisi = dataZapisi;
    }

    public String getVremyaNachala() {
        return vremyaNachala;
    }

    public void setVremyaNachala(String vremyaNachala) {
        this.vremyaNachala = vremyaNachala;
    }

    public String getVisit() {
        return visit;
    }

    public void setVisit(String visit) {
        this.visit = visit;
    }

    public com.example.Salon.models.Klient getKlient() {
        return Klient;
    }

    public void setKlient(com.example.Salon.models.Klient klient) {
        Klient = klient;
    }

    public com.example.Salon.models.Yslygi getYslygi() {
        return Yslygi;
    }

    public void setYslygi(com.example.Salon.models.Yslygi yslygi) {
        Yslygi = yslygi;
    }

    public com.example.Salon.models.Sotrydniki getSotrydniki() {
        return Sotrydniki;
    }

    public void setSotrydniki(com.example.Salon.models.Sotrydniki sotrydniki) {
        Sotrydniki = sotrydniki;
    }
}
