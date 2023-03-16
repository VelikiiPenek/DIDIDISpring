package com.example.Salon.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class AdresSalona {
    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty(message = "Поле не может быть пустым")
    private String adresSalona;
    @OneToOne(optional = true,mappedBy = "AdresSalona")
    private Salon salon;

    public AdresSalona() {
    }

    public AdresSalona(String adresSalona) {
        this.adresSalona = adresSalona;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdresSalona() {
        return adresSalona;
    }

    public void setAdresSalona(String adresSalona) {
        this.adresSalona = adresSalona;
    }
}
