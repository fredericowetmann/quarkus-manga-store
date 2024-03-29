package br.unitins.topicos2.repository;

import java.util.List;

import br.unitins.topicos2.model.Comic;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ComicRepository implements PanacheRepository<Comic>{
    public List<Comic> findByName(String name) {
        return find("UPPER(name) LIKE UPPER(?1) ", "%"+name+"%").list();
    }
}
