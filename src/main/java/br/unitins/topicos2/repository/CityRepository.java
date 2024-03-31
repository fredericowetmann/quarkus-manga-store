package br.unitins.topicos2.repository;

import java.util.List;

import br.unitins.topicos2.model.City;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CityRepository implements PanacheRepository<City> {

    public List<City> findByName(String name) {
        if (name == null)
            return null;
        return find("UPPER(name) LIKE ?1 ", "%" + name.toUpperCase() + "%").list();
    }

    public List<City> findAll2() {
        return find("SELECT c FROM City c ORDER BY c.name ").list();
    }

}