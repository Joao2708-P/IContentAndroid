package br.unicamp.apicontent.Models;

import com.google.gson.annotations.SerializedName;

public class ListMateria
{
    @SerializedName("materia")
    private String materia;
    public String getMateria()
    {
        return materia;
    }

    public void setMateria(String materia)

    {
        this.materia = materia;
    }
}
