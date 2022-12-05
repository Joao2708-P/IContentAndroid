package br.unicamp.apicontent.APIconf;

import java.util.List;

import br.unicamp.apicontent.Models.Atividades;
import br.unicamp.apicontent.Models.Content;
import br.unicamp.apicontent.Models.Nivel;
import br.unicamp.apicontent.Models.Token;
import br.unicamp.apicontent.Models.User;
import br.unicamp.apicontent.Models.LogUser;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Service
{
    ////// User Rotes ////////
    @GET("/users/get")
    Call<List<User>> getUser();

    @GET("/users/get/:email")
    Call<User>pegarUsuario(@Path("email")String email);

    @POST("/users")
    Call<User>postUsuario(@Body User user);

    @POST("/users/auth")
    Call<Token>logUser(@Body LogUser logUser);

    @DELETE("/users/delete/{email}")
    Call<User>excluirUser(@Path("email") String email);
    ///////////////////////////////////////////////////////////////////////

    //////// Content Rotes ////////
    @GET("/conteudo/get")
    Call<List<Content>> getConteudo();

    //////// Nivel Rotes ////////
    @GET("/nivel/get/{id}")
    Call<Nivel>getNivelPorNome(@Path("id")String id);

    @GET("/nivel/get")
    Call<List<Nivel>> getNveis();


    @GET("/atividade/get")
    Call<List<Atividades>> getAtividade();
}
