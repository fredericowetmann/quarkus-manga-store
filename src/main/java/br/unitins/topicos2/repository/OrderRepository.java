package br.unitins.topicos2.repository;


import br.unitins.topicos2.model.Order;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderRepository implements PanacheRepository<Order> {

    public PanacheQuery<Order> findAll(String login) {
        return find("user.login = ?1", login);
    }
    
    public PanacheQuery<Order> findAll(Long idUser) {
        return find("user.id = ?1", idUser);
    }
}
