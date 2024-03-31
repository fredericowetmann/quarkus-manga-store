package br.unitins.topicos2.repository;

import br.unitins.topicos2.model.Address;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;

@ApplicationScoped
public class AddressRepository implements PanacheRepository<Address>{
    public PanacheQuery<Address> findByCity(Long city){
        try{
            return find("city.id = ?1", city);
        } catch(NoResultException e){
            e.printStackTrace();
            return null;
        }
    }
}