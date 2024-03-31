package br.unitins.topicos2.repository;

import java.util.List;
import br.unitins.topicos2.model.Address;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;

@ApplicationScoped
public class AddressRepository implements PanacheRepository<Address>{
    public List<Address> findByCity(Long city){
        try{
            return find("city.id = ?1", city).list();
        } catch(NoResultException e){
            e.printStackTrace();
            return null;
        }
    }
}