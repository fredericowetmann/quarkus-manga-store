package br.unitins.topicos2.service;

import java.io.File;

public interface FileService {

    void save(Long id, String nomeImagem, byte[] imagem);

    File getFile(String fileName);
    
}
