package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.ComicDTO;
import br.unitins.topicos2.dto.ComicResponseDTO;
import br.unitins.topicos2.form.ComicImageForm;

public interface ComicService {

    public ComicResponseDTO insert(ComicDTO dto);

    public ComicResponseDTO update(Long id, ComicDTO dto);

    public ComicResponseDTO insertImage(Long id,String name);

    public void delete(Long id);

    public List<ComicResponseDTO> findAll();

    public List<ComicResponseDTO> findByName(String name);

    public ComicResponseDTO findById(Long id);
    
}