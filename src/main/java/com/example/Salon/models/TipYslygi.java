package com.example.Salon.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
public class TipYslygi {
    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty(message = "Поле не может быть пустым")
    private String tipYslygi;
    @OneToMany(mappedBy = "tipYslygi",fetch = FetchType.EAGER)
    private Collection<Yslygi> yslygiCollection;

    public TipYslygi() {
    }

    public TipYslygi(String tipYslygi, Collection<Yslygi> yslygiCollection) {
        this.tipYslygi = tipYslygi;
        this.yslygiCollection = yslygiCollection;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipYslygi() {
        return tipYslygi;
    }

    public void setTipYslygi(String tipYslygi) {
        this.tipYslygi = tipYslygi;
    }

    public Collection<Yslygi> getYslygiCollection() {
        return yslygiCollection;
    }

    public void setYslygiCollection(Collection<Yslygi> yslygiCollection) {
        this.yslygiCollection = yslygiCollection;
    }
}
