package br.unitins.topicos2.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Manga extends DefaultEntity{

    private String name;
    private String description;
    private Double price;
    private Integer numPages;
    private Integer volume;

    @Column(columnDefinition = "INT CHECK (inventory >= 0)")
    private Integer inventory;

    @ManyToOne
    @JoinColumn(name = "id_collection")
    private Collection collection;

    @ManyToOne
    @JoinColumn(name = "id_publisher")
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "id_author")
    private Author author;

    private String imageName;

    @ManyToMany
    @JoinTable(name = "manga_genre",
                joinColumns = @JoinColumn(name = "id_manga"),
                inverseJoinColumns = @JoinColumn(name = "id_genre"))
    private List<Genre> listGenre;

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

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
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

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public List<Genre> getListGenre() {
        return listGenre;
    }

    public void setListGenre(List<Genre> listGenre) {
        this.listGenre = listGenre;
    }
}
