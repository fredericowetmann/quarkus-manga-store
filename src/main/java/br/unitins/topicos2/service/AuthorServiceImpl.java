package br.unitins.topicos2.service;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos2.dto.AuthorDTO;
import br.unitins.topicos2.dto.AuthorResponseDTO;
import br.unitins.topicos2.model.Author;
import br.unitins.topicos2.repository.AuthorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AuthorServiceImpl implements AuthorService {

    @Inject
    AuthorRepository repository;

    @Override
    @Transactional
    public AuthorResponseDTO insert(AuthorDTO dto) {
        Author newAuthor = new Author();

        newAuthor.setName(dto.name());

        repository.persistAndFlush(newAuthor);

        return AuthorResponseDTO.valueOf(newAuthor);
    }

    @Override
    @Transactional
    public AuthorResponseDTO update(Long id, AuthorDTO dto) {
        if(repository.findById(id) == null) {
            throw new NotFoundException("Autor não encontrado");
        }

        Author author = repository.findById(id);
        author.setName(dto.name());

        return AuthorResponseDTO.valueOf(author);
    }

    @Override
    @Transactional
    public void delete(long id) {
        if(!repository.deleteById(id)){
            throw new NotFoundException("Autor não encontrado");
        }
    }

    @Override
    public AuthorResponseDTO findById(long id) {
        if(repository.findById(id) == null){
            throw new NotFoundException("Autor não encontrado");
        }
        return AuthorResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<AuthorResponseDTO> findByName(String name) {
        if(repository.findByName(name).stream().map(e -> AuthorResponseDTO.valueOf(e)).toList().isEmpty()){
            throw new NotFoundException("Autor não encontrado");
        }
        return repository.findByName(name).stream().map(e -> AuthorResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<AuthorResponseDTO> getAll(int page, int pageSize) {
        List<Author> list = repository
                                .findAll()
                                .page(page, pageSize)
                                .list();
        if(repository.listAll().stream().map(e -> AuthorResponseDTO.valueOf(e)).toList().isEmpty()){
            throw new NotFoundException("Não há autores");
        }
        return list.stream().map(e -> AuthorResponseDTO.valueOf(e)).collect(Collectors.toList());
    }  
    
    @Override
    public long count() {
        return repository.count();
    }
}
