package com.example.proyectodefinitivo

import android.content.Context

class Preferencias(c:Context) {
    val FIRE="fichero"
    val EMAIL="email"
    val NOMBREGRUPO="nomGrupo"
    val storage=c.getSharedPreferences(FIRE,0)//PARA QUE SOLO PUEDA ESCRIBIR EN ESE FICHERO

    public fun guardarEmail(email:String){
        storage.edit().putString(EMAIL,email).apply()
    }

    public fun leerEmail():String?{
        return storage.getString(EMAIL,null)
    }

    public fun guardarNomGrupo(nomGrupo:String){
        storage.edit().putString(NOMBREGRUPO,nomGrupo).apply()
    }

    public fun leerNomGrupo():String?{
        return storage.getString(NOMBREGRUPO,null)
    }

    public fun borrarTodo(){
        storage.edit().clear().apply()
    }
}