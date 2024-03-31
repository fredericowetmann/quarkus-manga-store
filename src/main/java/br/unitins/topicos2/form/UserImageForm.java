package br.unitins.topicos2.form;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

import jakarta.ws.rs.FormParam;

public class UserImageForm {

    @FormParam("nameImagem")
    private String nameImagem;

    @FormParam("imagem")
    @PartType("application/octet-stream")
    private byte[] imagem;

    public String getNameImagem() {
        return nameImagem;
    }

    public void setNameImagem(String nameImagem) {
        this.nameImagem = nameImagem;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    
    
}
