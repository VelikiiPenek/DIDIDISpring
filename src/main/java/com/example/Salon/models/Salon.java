package com.example.Salon.models;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
public class Salon {
    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty(message = "Поле не может быть пустым")
    private String naimenovanieSalona;
    @NotEmpty(message = "Поле не может быть пустым")
    private String ploshyad;
    @NotNull(message = "Поле не может быть пустым")
    private Integer kolVoMest;
    @NotEmpty(message = "Поле не может быть пустым")
    private String vremyaRaboti;
    @OneToOne(cascade = CascadeType.DETACH,optional = true)
    private AdresSalona AdresSalona;

    @OneToMany(mappedBy = "Salon",fetch = FetchType.EAGER)
    private Collection<Sotrydniki> sotrydnikiCollection;

    public Salon() {
    }

    public Salon(String naimenovanieSalona) {
        this.naimenovanieSalona = naimenovanieSalona;
    }

    public Salon(String ploshyad, Integer kolVoMest, String vremyaRaboti, com.example.Salon.models.AdresSalona adresSalona) {
        this.ploshyad = ploshyad;
        this.kolVoMest = kolVoMest;
        this.vremyaRaboti = vremyaRaboti;
        AdresSalona = adresSalona;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPloshyad() {
        return ploshyad;
    }

    public void setPloshyad(String ploshyad) {
        this.ploshyad = ploshyad;
    }

    public Integer getKolVoMest() {
        return kolVoMest;
    }

    public void setKolVoMest(Integer kolVoMest) {
        this.kolVoMest = kolVoMest;
    }

    public String getVremyaRaboti() {
        return vremyaRaboti;
    }

    public void setVremyaRaboti(String vremyaRaboti) {
        this.vremyaRaboti = vremyaRaboti;
    }

    public com.example.Salon.models.AdresSalona getAdresSalona() {
        return AdresSalona;
    }

    public void setAdresSalona(com.example.Salon.models.AdresSalona adresSalona) {
        AdresSalona = adresSalona;
    }

    public Collection<Sotrydniki> getSotrydnikiCollection() {
        return sotrydnikiCollection;
    }

    public void setSotrydnikiCollection(Collection<Sotrydniki> sotrydnikiCollection) {
        this.sotrydnikiCollection = sotrydnikiCollection;
    }

    public String getNaimenovanieSalona() {
        return naimenovanieSalona;
    }

    public void setNaimenovanieSalona(String naimenovanieSalona) {
        this.naimenovanieSalona = naimenovanieSalona;
    }
}
