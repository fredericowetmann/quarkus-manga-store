package br.unitins.topicos2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Genre extends DefaultEntity {
    private String name;
    @Column(length = 2000)
    private String description;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}