package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.MangaDTO;
import br.unitins.topicos2.dto.MangaResponseDTO;
// import br.unitins.topicos2.form.MangaImageForm;

public interface MangaService {

    public MangaResponseDTO insert(MangaDTO dto);

    public MangaResponseDTO update(Long id, MangaDTO dto);

    public MangaResponseDTO insertImage(Long id,String name);

    public void delete(Long id);

    public List<MangaResponseDTO> getAll(int page, int pageSize);

    public List<MangaResponseDTO> findByName(String name);

    public MangaResponseDTO findById(Long id);

    public List<MangaResponseDTO> findByAuthor(String authorName);
    
}