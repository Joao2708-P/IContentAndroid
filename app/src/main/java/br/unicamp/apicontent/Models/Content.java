package br.unicamp.apicontent.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Content implements Serializable
{
    @SerializedName("title")
    private String title;
    @SerializedName("corpo")
    private String corpo;
    @SerializedName("nivel_id")
    private String nivel_id;
    @SerializedName("image")
    private String image;

    public Content(String title, String corpo, String nivel_id, String image)
    {
        this.title = title;
        this.corpo = corpo;
        this.nivel_id = nivel_id;
        this.image = image;
    }

    public Content(String title, String corpo, String image)
    {
        this.title = title;
        this.corpo = corpo;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public String getNivel_id() {
        return nivel_id;
    }

    public void setNivel_id(String nivel_id) {
        this.nivel_id = nivel_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
