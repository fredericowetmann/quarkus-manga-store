package br.unitins.topicos2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "mangas")
public class Manga extends DefaultEntity {
    private String name;
    private String description;
    private Double price;
    private Integer numPages;
    private Integer volume;

    @Column(columnDefinition = "INT CHECK (inventory >= 0)")
    private Integer inventory;
    
    @ManyToOne
    @JoinColumn(name = "id_author")
    private Author author;

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
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Integer getNumPages() {
        return numPages;
    }
    public void setNumPages(Integer numPages) {
        this.numPages = numPages;
    }
    public Integer getVolume() {
        return volume;
    }
    public void setVolume(Integer volume) {
        this.volume = volume;
    }
    public Integer getInventory() {
        return inventory;
    }
    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }
    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }
}
