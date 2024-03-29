package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.GenreDTO;
import br.unitins.topicos2.dto.GenreResponseDTO;
import br.unitins.topicos2.model.Genre;
import br.unitins.topicos2.repository.GenreRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class GenreServiceImpl implements GenreService{

    @Inject
    GenreRepository repository;

    @Override
    @Transactional
    public GenreResponseDTO insert(GenreDTO dto) {
        Genre genre = new Genre();

        genre.setName(dto.name());

        repository.persist(genre);

        return GenreResponseDTO.valueOf(genre);
    }

    @Override
    @Transactional
    public GenreResponseDTO update(Long id, GenreDTO dto) {
        Genre genre = repository.findById(id);

        genre.setName(dto.name());

        return GenreResponseDTO.valueOf(genre);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if(!repository.deleteById(id)){
            throw new NotFoundException();
        }
    }

    @Override
    public GenreResponseDTO findById(Long id) {
        return GenreResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<GenreResponseDTO> findByName(String name) {
        return repository.findByName(name).stream().map(e -> GenreResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<GenreResponseDTO> findAll() {
        return repository.listAll().stream().map(e -> GenreResponseDTO.valueOf(e)).toList();
    }
    
}
