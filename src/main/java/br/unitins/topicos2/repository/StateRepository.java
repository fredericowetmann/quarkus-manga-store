package br.unitins.topicos2.repository;

import br.unitins.topicos2 .model.State;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StateRepository implements PanacheRepository<State>{
    
}
