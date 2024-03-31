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
public class GenreServiceImpl implements GenreService {

    @Inject
    GenreRepository repository;

    @Override
    @Transactional
    public GenreResponseDTO insert(GenreDTO dto) {
        Genre newGenre = new Genre();

        newGenre.setName(dto.name());
        newGenre.setDescription(dto.description());

        repository.persistAndFlush(newGenre);

        return GenreResponseDTO.valueOf(newGenre);
    }

    @Override
    @Transactional
    public GenreResponseDTO update(Long id, GenreDTO dto) {
        if(repository.findById(id) == null) {
            throw new NotFoundException("Genero não encontrado");
        }

        Genre genre = repository.findById(id);
        genre.setName(dto.name());
        genre.setDescription(dto.description());

        return GenreResponseDTO.valueOf(genre);
    }

    @Override
    @Transactional
    public void delete(long id) {
        if(!repository.deleteById(id)){
            throw new NotFoundException("Genero não encontrado");
        }
    }

    @Override
    public GenreResponseDTO findById(long id) {
        if(repository.findById(id) == null){
            throw new NotFoundException("Genero não encontrado");
        }
        return GenreResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<GenreResponseDTO> findByName(String name) {
        if(repository.findByName(name).stream().map(e -> GenreResponseDTO.valueOf(e)).toList().isEmpty()){
            throw new NotFoundException("Genero não encontrado");
        }
        return repository.findByName(name).stream().map(e -> GenreResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<GenreResponseDTO> findAll() {
        if(repository.listAll().stream().map(e -> GenreResponseDTO.valueOf(e)).toList().isEmpty()){
            throw new NotFoundException("Não há generos");
        }
        return repository.listAll().stream().map(e -> GenreResponseDTO.valueOf(e)).toList();
    }
    
}
