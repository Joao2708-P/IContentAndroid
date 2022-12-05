package br.unicamp.apicontent.Models;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable
{
    @SerializedName("email")
    private String email;
    @SerializedName("nome")
    private String nome;
    @SerializedName("senha")
    private String  senha;
    @SerializedName("nivel_id")
    private String nivelEscolar;
    @SerializedName("image")
    private String image;

    public User()
    {}

    public User(String email, String nome, String senha, String nivelEscolar, String image)
    {
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.nivelEscolar = nivelEscolar;
        this.image = image;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNivelEscolar() {
        return nivelEscolar;
    }

    public void setNivelEscolar(String nivelEscolar) {
        this.nivelEscolar = nivelEscolar;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
