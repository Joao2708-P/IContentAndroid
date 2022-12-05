package br.unicamp.apicontent.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LogUser implements Serializable
{
    @SerializedName("email")
    private String email;
    @SerializedName("senha")
    private String senha;

    public LogUser(String email, String senha)
    {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
