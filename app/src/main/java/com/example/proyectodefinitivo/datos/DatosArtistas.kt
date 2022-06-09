package com.example.proyectodefinitivo.datos

import com.google.gson.annotations.SerializedName

data class DatosArtistas(
    //SerializedName para renombrar las variable y poder trabajar con ellas

    @SerializedName("strArtist") var nombre:String,
    @SerializedName("strStyle") var estilo:String,
    @SerializedName("strGenre") var genero:String,
    @SerializedName("strCountry") var pais:String,
    @SerializedName("intBornYear") var anio:String,
    @SerializedName("strArtistLogo") var logo:String,
    @SerializedName("strArtistThumb") var imagen:String,
)

data class Datos(
    @SerializedName("artists") var grupo:Array<DatosArtistas>
)
