package br.unitins.topicos2.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import br.unitins.topicos2.model.Manga;
import br.unitins.topicos2.repository.MangaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import br.unitins.topicos2.validation.ValidationException;

@ApplicationScoped
public class MangaFileService implements FileService {

    // ex. /Users/user/quarkus/images/manga/
    private final String PATH_USER = System.getProperty("user.home")
        + File.separator + "quarkus"
        + File.separator + "images"
        + File.separator + "manga" + File.separator;

        @Inject
        MangaRepository mangaRepository;
    
        @Override
        @Transactional
        public void save(Long id, String nomeImagem, byte[] imagem) {
            Manga manga = mangaRepository.findById(id);
    
            try {
                String novoNomeImagem = salvarImagem(imagem, nomeImagem);
                manga.setImageName(novoNomeImagem);
                // excluir a imagem antiga (trabalho pra quem????)
            } catch (IOException e) {
                throw new ValidationException("imagem", e.toString());
            }
        }
    
        private String salvarImagem(byte[] imagem, String nomeImagem) throws IOException {
        
            // verificando o tipo da imagem
            String mimeType = Files.probeContentType(new File(nomeImagem).toPath());
            List<String> listMimeType = Arrays.asList("image/jpg", "image/jpeg", "image/png", "image/gif");
            if (!listMimeType.contains(mimeType)) {
                throw new IOException("Tipo de imagem não suportada.");
            }
    
            // verificando o tamanho do arquivo - nao permitir maior que 10 megas
            if (imagem.length > (1024 * 1024 * 10))
                throw new IOException("Arquivo muito grande.");
    
            // criando as pastas quando não existir
            File diretorio = new File(PATH_USER);
            if (!diretorio.exists())
                diretorio.mkdirs();
    
            // gerando o nome do arquivo
            String nomeArquivo = UUID.randomUUID()
            +"."+mimeType.substring(mimeType.lastIndexOf("/")+1);
    
            String path = PATH_USER + nomeArquivo;
    
            // salvando o arquivo
            File file = new File(path);
            // alunos (melhorar :)
            if (file.exists())
                throw new IOException("O nome gerado da imagem está repetido.");
    
            // criando um arquivo no S.O.
            file.createNewFile();
    
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(imagem);
            // garantindo o envio do ultimo lote de bytes
            fos.flush();
            fos.close();
    
            return nomeArquivo;
        
        }
    
        @Override
        public File getFile(String fileName) {
            File file = new File(PATH_USER+fileName);
            return file;
        }
}