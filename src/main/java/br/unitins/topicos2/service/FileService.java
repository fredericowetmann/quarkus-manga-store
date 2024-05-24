package br.unitins.topicos2.service;

import java.io.File;
import java.io.IOException;

public interface FileService {

    String save(String nomeArquivo, byte[] arquivo) throws IOException;

    File getFile(String nomeArquivo); 
    
}
