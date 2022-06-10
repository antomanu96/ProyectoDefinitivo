package com.example.proyectodefinitivo.recycledValoraciones

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
class DatosValoraciones(var email:String?=null, var comentario:String?=null):Serializable {
}