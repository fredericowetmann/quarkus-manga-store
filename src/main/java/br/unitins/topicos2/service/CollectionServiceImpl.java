package br.unitins.topicos2.service;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos2.dto.CollectionDTO;
import br.unitins.topicos2.dto.CollectionResponseDTO;
import br.unitins.topicos2.model.Collection;
import br.unitins.topicos2.repository.CollectionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CollectionServiceImpl implements CollectionService {

    @Inject
    CollectionRepository repository;

    @Override
    @Transactional
    public CollectionResponseDTO insert(CollectionDTO dto) {
        Collection newCollection = new Collection();

        newCollection.setName(dto.name());
        newCollection.setDescription(dto.description());

        repository.persistAndFlush(newCollection);

        return CollectionResponseDTO.valueOf(newCollection);
    }

    @Override
    @Transactional
    public CollectionResponseDTO update(Long id, CollectionDTO dto) {
        if(repository.findById(id) == null) {
            throw new NotFoundException("Coleção não encontrada");
        }

        Collection collection = repository.findById(id);
        collection.setName(dto.name());
        collection.setDescription(dto.description());

        return CollectionResponseDTO.valueOf(collection);
    }

    @Override
    @Transactional
    public void delete(long id) {
        if(!repository.deleteById(id)){
            throw new NotFoundException("Coleção não encontrada");
        }
    }

    @Override
    public CollectionResponseDTO findById(long id) {
        if(repository.findById(id) == null){
            throw new NotFoundException("Coleção não encontrada");
        }
        return CollectionResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<CollectionResponseDTO> findByName(String name) {
        if(repository.findByName(name).stream().map(e -> CollectionResponseDTO.valueOf(e)).toList().isEmpty()){
            throw new NotFoundException("Coleção não encontrada");
        }
        return repository.findByName(name).stream().map(e -> CollectionResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<CollectionResponseDTO> getAll(int page, int pageSize) {
        List<Collection> list = repository
                                .findAll()
                                .page(page, pageSize)
                                .list();
        if(repository.listAll().stream().map(e -> CollectionResponseDTO.valueOf(e)).toList().isEmpty()){
            throw new NotFoundException("Não há Coleções");
        }    
        return list.stream().map(e -> CollectionResponseDTO.valueOf(e)).collect(Collectors.toList());
    }  

}
