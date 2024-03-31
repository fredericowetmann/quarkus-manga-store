package br.unitins.topicos2.repository;

import java.util.List;

import br.unitins.topicos2.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User>{
    public List<User> findByName(String name) {
        return find("UPPER(name) LIKE UPPER(?1) ", "%"+name+"%").list();
    }

    public User findByLogin(String login) {
        try {
            return find("login = ?1 ", login ).singleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
        
    }

    public User findByLoginAndPassword(String login, String password) {
        try {
            return find("login = ?1 AND password = ?2 ", login, password).singleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
        
    }
}
