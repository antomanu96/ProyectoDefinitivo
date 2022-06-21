package com.example.proyectodefinitivo.recycledChat

class Mensaje {
    var msj:String=""
    var email:String=""
    var fecha= System.currentTimeMillis()

    constructor()

    constructor(mensaje: String, mail:String){
        msj=mensaje
        email=mail
    }

}