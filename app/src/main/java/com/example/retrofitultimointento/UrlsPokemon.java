package com.example.retrofitultimointento;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
public interface UrlsPokemon {


    @GET("/pokemon/{namePokemon}")
    void wsGetPokemon(@Path("namePokemon") String namePokemon, Callback<String> cb);

}
