package br.unitins.topicos2.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User extends DefaultEntity {

    private String name;
    private String login;
    private String password;
    //@Enumerated(EnumType.ORDINAL)
    private Profile profile;

    private String nameImagem;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinTable(name = "user_phone", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_phone"))
    private List<Phone> listaPhone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Phone> getListaPhone() {
        return listaPhone;
    }

    public void setListaPhone(List<Phone> listaPhone) {
        this.listaPhone = listaPhone;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getNameImagem() {
        return nameImagem;
    }

    public void setNameImagem(String nameImagem) {
        this.nameImagem = nameImagem;
    }
}
