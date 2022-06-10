package com.example.proyectodefinitivo.recycledValoraciones

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class DatosValoraciones(var comentario:String?=null,var email:String?=null
):Serializable {


}