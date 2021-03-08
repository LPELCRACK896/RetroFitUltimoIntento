package com.example.retrofitultimointento;

import com.squareup.okhttp.OkHttpClient;

import java.lang.reflect.Type;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.Converter;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

public class ApiRest {
    /*Aquí se coloca que interface se utilizará para las urls de los ws. (Alt+j): para seleccionar la misma palabra*/
    final private UrlsPokemon urls;
    public ApiRest()
    {
        //Recuerden cambiar la URL_BASE

        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(45, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(45, TimeUnit.SECONDS);
        RestAdapter restAdapter= new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                /*Aquí cambian la URL_BASE*/.setEndpoint(Utils.URL_BASE)
                .setConverter(new StringConverter2())
                .setClient(new OkClient(okHttpClient))
                .build();
        urls= restAdapter.create(UrlsPokemon.class);
    }

    static class StringConverter2 implements Converter {
        //Parsear la respuesta a una cadena
        @Override
        public Object fromBody(TypedInput typedInput, Type type) {
            try {
                Scanner scanner = new Scanner(typedInput.in()).useDelimiter("\\A");
                return scanner.hasNext() ? scanner.next() : "";
            }
            catch(Exception ex) {
                Objects.requireNonNull(ex.getCause()).printStackTrace();
                return null;
            }
        }
        @Override
        public TypedOutput toBody(Object o) {
            return null;
        }
    }
    public UrlsPokemon getService() {
        return  urls;
    }
}