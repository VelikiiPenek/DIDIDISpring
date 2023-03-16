package com.example.Salon.models;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
public class Sklad {
    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty(message = "Поле не может быть пустым")
    private String adresSklada;
    @OneToMany(mappedBy = "adressSklada",fetch = FetchType.EAGER)
    private Collection<Nabori> naboriCollection;

    public Sklad() {
    }

    public Sklad(String adresSklada) {
        this.adresSklada = adresSklada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdresSklada() {
        return adresSklada;
    }

    public void setAdresSklada(String adresSklada) {
        this.adresSklada = adresSklada;
    }
}
