package br.unitins.topicos2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class State extends DefaultEntity {
    
    @Column(nullable = false, length = 60)
    private String name;

    @Column(nullable = false, length = 2)
    private String acronym;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

}