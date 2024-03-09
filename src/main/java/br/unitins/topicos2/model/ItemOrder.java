package br.unitins.topicos2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemOrder extends DefaultEntity {
    
    private int quantity;
    private double price;
    private boolean isBuyed = false;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_comic")
    private Comic comic;

    @ManyToOne
    @JoinColumn(name = "id_order")
    private Order order;

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Comic getComic() {
        return comic;
    }
    public void setComic(Comic comic) {
        this.comic = comic;
    }
    public boolean isIdBuyed() {
        return isBuyed;
    }
    public void setIdBuyed(boolean idBuyed) {
        this.isBuyed = idBuyed;
    }
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
}
