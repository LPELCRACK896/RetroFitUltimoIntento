package com.example.retrofitultimointento;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView spritePokemon;
        spritePokemon = findViewById(R.id.sprite);
        EditText editPokemon;
        TextView btn_ingresar, mostrar;
        btn_ingresar = findViewById(R.id.btn_ingresar);
        editPokemon = findViewById(R.id.edit_pokemon);
        mostrar = findViewById(R.id.respuesta);

        btn_ingresar.setOnClickListener(v -> {

            ApiRest apiRest =  new ApiRest();

            apiRest.getService().wsGetPokemon(editPokemon.getText().toString().toLowerCase(),new Callback<String>() {
                @Override
                public void success(String s, Response response) {
                    if (response.getStatus()==200){

                        try {
                            JSONObject data = new JSONObject(s);
                            String imprimirInfo =
                                    "Nombre: "+editPokemon.getText().toString()+"\n"+
                                    "Habilidad: " + data.optJSONArray("abilities").optJSONObject(0).getJSONObject("ability").optString("name")
                                            +"\nExperiencia base: " +  data.optString("base_experience");
                            //String sprite= data.optJSONArray("sprites").optJSONObject(0).optString("front_default");
                            //Picasso.get().load(sprite).into(spritePokemon);
                            mostrar.setText(imprimirInfo);

                            Log.i("detalle", data.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "Ocurrió un problema con el servidor", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });

        });

    }
}