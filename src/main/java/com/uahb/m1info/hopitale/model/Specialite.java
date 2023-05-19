package com.uahb.m1info.hopitale.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Specialite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 40, nullable = false)
    private String libelle;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @JsonIgnore
    @ManyToMany(mappedBy = "specialites")
    private List<Medecin> medecins;

    @JsonIgnore
    public List<Medecin> getMedecins() {
        return medecins;
    }

    public void setMedecins(List<Medecin> medecins) {
        this.medecins = medecins;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return libelle;
    }
}
