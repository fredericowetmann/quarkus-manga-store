package br.unitins.topicos2.repository;


import br.unitins.topicos2.model.Manga;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MangaRepository implements PanacheRepository<Manga>{
    public PanacheQuery<Manga> findByName(String name) {
        if (name == null)
            return null;
        return find("UPPER(name) LIKE ?1 ", "%" + name.toUpperCase() + "%");
    }

    public PanacheQuery<Manga> findByAuthor(String name) {
        if (name == null)
            return null;
        return find("UPPER(author.name) LIKE ?1 ", "%" + name.toUpperCase() + "%");
    }

    public PanacheQuery<Manga> findByPublisher(String name) {
        if (name == null)
            return null;
        return find("UPPER(publisher.name) LIKE ?1 ", "%" + name.toUpperCase() + "%");
    }

    public PanacheQuery<Manga> findByCollection(String name) {
        if (name == null)
            return null;
        return find("UPPER(collection.name) LIKE ?1 ", "%" + name.toUpperCase() + "%");
    }
}
