package br.unicamp.apicontent.Models;

import com.google.gson.annotations.SerializedName;

public class Atividades
{
    @SerializedName("quest")
    String quest;
    @SerializedName("resp")
    String resp;
    @SerializedName("materia_id")
    String materia_id;
    @SerializedName("user_id")
    String user_id;

    public Atividades(String quest, String resp, String materia_id, String user_id)
    {
        this.quest = quest;
        this.resp = resp;
        this.materia_id = materia_id;
        this.user_id = user_id;
    }

    public String getQuest()
    {
        return quest;
    }

    public void setQuest(String quest)
    {
        this.quest = quest;
    }

    public String getResp()
    {
        return resp;
    }

    public void setResp(String resp)
    {
        this.resp = resp;
    }

    public String getMateria_id()
    {
        return materia_id;
    }

    public void setMateria_id(String materia_id)
    {
        this.materia_id = materia_id;
    }

    public String getUser_id()
    {
        return user_id;
    }

    public void setUser_id(String user_id)
    {
        this.user_id = user_id;
    }
}
