package com.example.proyectodefinitivo.apiArtistas

import com.example.proyectodefinitivo.datos.Datos
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    //metodo con el que accedemos a nuestro api
    @GET
    suspend fun getDatos(@Url url:String): Response<Datos>
}