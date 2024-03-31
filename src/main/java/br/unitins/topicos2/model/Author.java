package br.unitins.topicos2.model;

import jakarta.persistence.Entity;

@Entity
public class Author extends DefaultEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}