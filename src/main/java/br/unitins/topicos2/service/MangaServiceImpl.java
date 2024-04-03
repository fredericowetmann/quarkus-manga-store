package br.unitins.topicos2.service;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import br.unitins.topicos2.dto.MangaDTO;
import br.unitins.topicos2.dto.MangaResponseDTO;
import br.unitins.topicos2.model.Genre;
// import br.unitins.topicos2.form.MangaImageForm;
import br.unitins.topicos2.model.Manga;
import br.unitins.topicos2.repository.AuthorRepository;
import br.unitins.topicos2.repository.CollectionRepository;
import br.unitins.topicos2.repository.GenreRepository;
import br.unitins.topicos2.repository.MangaRepository;
import br.unitins.topicos2.repository.PublisherRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class MangaServiceImpl implements MangaService{

    @Inject
    MangaRepository repository;

    @Inject
    PublisherRepository publisherRepository;

    @Inject
    CollectionRepository collectionRepository;

     @Inject
     AuthorRepository authorRepository;

     @Inject
     GenreRepository genreRepository;

    // @Inject
    // MangaFileService fileService;

    @Override
    @Transactional
    public MangaResponseDTO insert(MangaDTO dto) {
        Manga manga = new Manga();

        manga.setName(dto.name());
        manga.setDescription(dto.description());
        manga.setPrice(dto.price());
        manga.setInventory(dto.inventory());
        manga.setNumPages(dto.numPages());
        manga.setVolume(dto.volume());

        manga.setCollection(collectionRepository.findById(dto.collection()));
        manga.setPublisher(publisherRepository.findById(dto.publisher()));
        manga.setAuthor(authorRepository.findById(dto.author()));

        List<Genre> genres = new ArrayList<>();
        for (Long genreId : dto.listGenre()) {
        Genre genre = genreRepository.findById(genreId);
        if (genre != null) {
            genres.add(genre);
            }   
        }
        manga.setListGenre(genres);

        repository.persist(manga);

        return MangaResponseDTO.valueOf(manga);
    }

    @Override
    @Transactional
    public MangaResponseDTO update(Long id, MangaDTO dto) {

        if(repository.findById(id) == null){
            throw new NotFoundException("Produto não encontrado");
        }

        Manga manga = repository.findById(id);

        manga.setName(dto.name());
        manga.setDescription(dto.description());
        manga.setPrice(dto.price());
        manga.setInventory(dto.inventory());
        manga.setNumPages(dto.numPages());
        manga.setVolume(dto.volume());
        
        manga.setCollection(collectionRepository.findById(dto.collection()));
        manga.setPublisher(publisherRepository.findById(dto.publisher()));
        manga.setAuthor(authorRepository.findById(dto.author()));

        List<Genre> genres = new ArrayList<>();
        for (Long genreId : dto.listGenre()) {
        Genre genre = genreRepository.findById(genreId);
        if (genre != null) {
            genres.add(genre);
            }   
        }
        manga.setListGenre(genres);

        return MangaResponseDTO.valueOf(manga);
    }

    @Override
    @Transactional
    public MangaResponseDTO insertImage(Long id, String name){
        if(repository.findById(id) == null){
            throw new NotFoundException("Produto não encontrado");
        }

        Manga manga = repository.findById(id);
        // manga.setImageName(name);

        return MangaResponseDTO.valueOf(manga);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if(!repository.deleteById(id)){
            throw new NotFoundException("Produto não encontrado");
        }
    }

    @Override
    public List<MangaResponseDTO> getAll(int page, int pageSize) {
        List<Manga> list = repository
                                .findAll()
                                .page(page, pageSize)
                                .list();
        if(repository.listAll().stream().map(e -> MangaResponseDTO.valueOf(e)).toList().isEmpty()){
            throw new NotFoundException("Nenhum produto para ser encontrado");
        }
        
        return list.stream().map(e -> MangaResponseDTO.valueOf(e)).collect(Collectors.toList());
    }  

    @Override
    public MangaResponseDTO findById(Long id) {
        if (repository.findById(id) == null) {
            throw new NotFoundException("Produto não encontrado");
        }

        return MangaResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<MangaResponseDTO> findByName(String name) {

        if(repository.findByName(name).stream().map(e -> MangaResponseDTO.valueOf(e)).toList().isEmpty()){
            throw new NotFoundException("Nenhum produto encontrado");
        }

        return repository.findByName(name).stream().map(e -> MangaResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<MangaResponseDTO> findByAuthor(String authorName) {

        if(repository.findByAuthor(authorName).stream().map(e -> MangaResponseDTO.valueOf(e)).toList().isEmpty()){
            throw new NotFoundException("Nenhum produto encontrado");
        }

        return repository.findByAuthor(authorName).stream().map(e -> MangaResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<MangaResponseDTO> findByPublisher(String publisherName) {

        if(repository.findByPublisher(publisherName).stream().map(e -> MangaResponseDTO.valueOf(e)).toList().isEmpty()){
            throw new NotFoundException("Nenhum produto encontrado");
        }

        return repository.findByPublisher(publisherName).stream().map(e -> MangaResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<MangaResponseDTO> findByCollection(String collectionName) {

        if(repository.findByCollection(collectionName).stream().map(e -> MangaResponseDTO.valueOf(e)).toList().isEmpty()){
            throw new NotFoundException("Nenhum produto encontrado");
        }

        return repository.findByCollection(collectionName).stream().map(e -> MangaResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<MangaResponseDTO> findByGenre(Long id) {
        if(repository.findByGenre(id).stream().map(e -> MangaResponseDTO.valueOf(e)).toList().isEmpty()){
            throw new NotFoundException("Nenhum produto encontrado");
        }

        return repository.findByGenre(id).stream().map(e -> MangaResponseDTO.valueOf(e)).toList();
    }

    @Override
    public long count() {
        return repository.count();
    }
}