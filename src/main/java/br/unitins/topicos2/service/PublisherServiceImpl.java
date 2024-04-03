package br.unitins.topicos2.service;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos2.dto.PublisherDTO;
import br.unitins.topicos2.dto.PublisherResponseDTO;
import br.unitins.topicos2.model.Publisher;
import br.unitins.topicos2.repository.PublisherRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PublisherServiceImpl implements PublisherService{

    @Inject
    PublisherRepository repository;

    @Override
    @Transactional
    public PublisherResponseDTO insert(PublisherDTO dto) {
        Publisher Publisher = new Publisher();

        Publisher.setName(dto.name());

        repository.persist(Publisher);

        return PublisherResponseDTO.valueOf(Publisher);
    }

    @Override
    @Transactional
    public PublisherResponseDTO update(Long id, PublisherDTO dto) {
        if(repository.findById(id) == null){
            throw new NotFoundException("Publisher não encontrada");
        }

        Publisher Publisher = repository.findById(id);

        Publisher.setName(dto.name());

        return PublisherResponseDTO.valueOf(Publisher);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if(!repository.deleteById(id)){
            throw new NotFoundException("Publisher não encontrada");
        }
    }

    @Override
    public PublisherResponseDTO findById(Long id) {
        if(repository.findById(id) == null){
            throw new NotFoundException("Publisher não encontrada");
        }
        return PublisherResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<PublisherResponseDTO> findByName(String name) {
        if(repository.findByName(name).stream().map(e -> PublisherResponseDTO.valueOf(e)).toList().isEmpty()){
            throw new NotFoundException("Publisher não encontrada");
        }
        return repository.findByName(name).stream().map(e -> PublisherResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<PublisherResponseDTO> getAll(int page, int pageSize) {
        List<Publisher> list = repository
                                .findAll()
                                .page(page, pageSize)
                                .list();
        if(repository.listAll().stream().map(e -> PublisherResponseDTO.valueOf(e)).toList().isEmpty()) {
            throw new NotFoundException("Publisher não encontrada");
            }
        return list.stream().map(e -> PublisherResponseDTO.valueOf(e)).collect(Collectors.toList());
    }  
    
    @Override
    public long count() {
        return repository.count();
    }
}