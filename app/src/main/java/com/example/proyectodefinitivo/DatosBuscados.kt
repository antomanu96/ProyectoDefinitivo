package com.example.proyectodefinitivo

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable


@IgnoreExtraProperties
//Clase para luego subir a firebase

data class DatosBuscados (var nomGrupo:String?=null,var email:String?=null):Serializable{

}