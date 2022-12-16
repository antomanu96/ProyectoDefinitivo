package com.example.proyectodefinitivo.recycledChat

import java.text.SimpleDateFormat
import java.util.*

class Mensaje {
    var msj:String=""
    var email:String=""
    var fecha= convertirFecha()
    var imagen:String=""

    constructor()

    constructor(mensaje: String, mail:String,imag:String){
        msj=mensaje
        email=mail
        imagen=imag
    }


    private fun convertirFecha(): String{
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        return currentDate
    }
}