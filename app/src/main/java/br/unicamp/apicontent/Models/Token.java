package br.unicamp.apicontent.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Token implements Serializable
{
    @SerializedName("token")
    private String token;

    public Token(String token)
    {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
