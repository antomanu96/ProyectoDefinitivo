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
/**
    fun traerArtista(item:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java)
                .getDatos("$RESTO_URL$item")//para concatenar
            val datos=call.body()
            //para quitar las cabeceras de http tipicas
            //a.runOnUiThread {
            if(call.isSuccessful){
                val array = datos?.grupo?: emptyArray()
                println("--------->" + array[0].toString())
                val artista = array[0]

                println("******ARTISTA******"+artista)

                //artistaList.add(artista)
                render(artista)

                TimeUnit.SECONDS.sleep(1L)
            }
            else{
                //error()
            }
            //}

        }
    }

    //---------------------------------------------------------------------------------------

    fun render(artista: DatosArtistas){
        binding.tvNombre.text=artista.nombre
        binding.tvEstilo.text=artista.estilo
        binding.tvGenero.text=artista.genero
        binding.tvAniofun.text=artista.anio
        binding.tvPais.text=artista.pais
        Picasso.get().load(artista.logo).into(binding.ivGrupo)
        Picasso.get().load(artista.imagen).into(binding.ivLogo)

    }
*/

}