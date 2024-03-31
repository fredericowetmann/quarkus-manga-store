package br.unitins.topicos2.repository;

import br.unitins.topicos2.model.State;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StateRepository implements PanacheRepository<State> {

    public PanacheQuery<State> findByName(String name) {
        if (name == null)
            return null;
        return find("UPPER(name) LIKE ?1 ", "%" + name.toUpperCase() + "%");
    }

}