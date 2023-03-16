package com.example.Salon.models;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
public class Nabori {
    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty(message = "Поле не может быть пустым")
    private String nameKit;

    @ManyToOne(optional = true,cascade = CascadeType.DETACH)
    private Sklad adressSklada;

    @OneToMany(mappedBy = "nabori",fetch = FetchType.EAGER)
    private Collection<Yslygi> yslygiCollection;

    public Nabori() {
    }

    public Nabori(String nameKit, Sklad adressSklada, Collection<Yslygi> yslygiCollection) {
        this.nameKit = nameKit;
        this.adressSklada = adressSklada;
        this.yslygiCollection = yslygiCollection;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameKit() {
        return nameKit;
    }

    public void setNameKit(String nameKit) {
        this.nameKit = nameKit;
    }

    public Sklad getAdressSklada() {
        return adressSklada;
    }

    public void setAdressSklada(Sklad adressSklada) {
        this.adressSklada = adressSklada;
    }

    public Collection<Yslygi> getYslygiCollection() {
        return yslygiCollection;
    }

    public void setYslygiCollection(Collection<Yslygi> yslygiCollection) {
        this.yslygiCollection = yslygiCollection;
    }
}
