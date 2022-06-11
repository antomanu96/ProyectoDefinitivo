package com.example.proyectodefinitivo.recycled

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class DatosBuscados (var nomGrupo:String?=null,var email:String?=null):Serializable{

}
