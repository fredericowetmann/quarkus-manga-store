package br.unitins.topicos2.repository;

import br.unitins.topicos2.model.User;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User>{
    
    public PanacheQuery<User> findByName(String name) {
        if (name == null)
            return null;
        return find("UPPER(name) LIKE ?1 ", "%" + name.toUpperCase() + "%");
    }

    public User findByEmail(String email) {
        try {
            return find("email = ?1 ", email ).singleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
        
    }

    public User findByEmailAndPassword(String email, String password) {
        try {
            return find("email = ?1 AND password = ?2 ", email, password).singleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
        
    }

    public boolean existsByEmail(String email) {
        return find("email", email).count() > 0;
    }
}
