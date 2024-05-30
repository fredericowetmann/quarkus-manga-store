package br.unitins.topicos2.form;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

import jakarta.ws.rs.FormParam;

public class UserImageForm {

    @FormParam("imageName")
    private String imageName;

    @FormParam("image")
    @PartType("application/octet-stream")
    private byte[] image;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

}
