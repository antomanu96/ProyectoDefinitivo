package com.example.proyectodefinitivo.apiArtistas


import android.app.Activity
import android.widget.SearchView
import com.example.proyectodefinitivo.databinding.ActivityBuscarGruposBinding
import com.example.proyectodefinitivo.datos.DatosArtistas
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiProvider {
    val BASE_URL="https://www.theaudiodb.com/"
    val RESTO_URL="api/v1/json/2/search.php?s="


   // val binding: ActivityBuscarGruposBinding
        //TODO: implement later
        //get() {
            //TODO()
        //}


    //esta funcion es siempre igual
    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}