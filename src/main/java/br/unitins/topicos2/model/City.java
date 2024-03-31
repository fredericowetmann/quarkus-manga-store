package br.unitins.topicos2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class City extends DefaultEntity {
    
    @Column(nullable = false, length = 60)
    private String name;

    @JoinColumn(name = "id_state")
    @ManyToOne
    private State state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

}