package br.unitins.topicos2.repository;

import java.util.List;

import br.unitins.topicos2.model.Publisher;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PublisherRepository implements PanacheRepository<Publisher>{
    public List<Publisher> findByName(String name) {
        return find("UPPER(name) LIKE UPPER(?1) ", "%"+name+"%").list();
    }
    
}
