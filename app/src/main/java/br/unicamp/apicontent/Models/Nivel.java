package br.unicamp.apicontent.Models;

import com.google.gson.annotations.SerializedName;

public class Nivel
{
    @SerializedName("nivel")
    String nivel;
    @SerializedName("id")
    String id;

    public Nivel(String nivel, String id)
    {
        this.id = id;
        this.nivel = nivel;
    }

    public String getNivel() {
        return this.nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
