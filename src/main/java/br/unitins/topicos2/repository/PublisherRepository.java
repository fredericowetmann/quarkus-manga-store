package br.unitins.topicos2.repository;

import br.unitins.topicos2.model.Publisher;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PublisherRepository implements PanacheRepository<Publisher>{
    
    public PanacheQuery<Publisher> findByName(String name) {
        if (name == null)
            return null;
        return find("UPPER(name) LIKE ?1 ", "%" + name.toUpperCase() + "%");
    }
    
}
