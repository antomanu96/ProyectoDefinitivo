package com.example.proyectodefinitivo.recycledValoraciones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyectodefinitivo.Preferencias
import com.example.proyectodefinitivo.R
import com.example.proyectodefinitivo.databinding.ActivityAniadirComentarioBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AniadirComentarioActivity : AppCompatActivity() {
    lateinit var binding: ActivityAniadirComentarioBinding
    lateinit var pref:Preferencias
    var email=""
    var comentario=""
    private lateinit var db: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAniadirComentarioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListener()
    }

    private fun setListener() {
        binding.btnVolverAniadir.setOnClickListener {
            finish()
        }

        binding.btnAniadir.setOnClickListener {
            //boton para añadir comentario cuando este escrito
            //tener en cuenta eso que este escrito el comentario
            guardarComentario()
            
        }
    }

    private fun guardarComentario(){
        pref= Preferencias(this)
        //email= pref.leerEmail().toString()
        //comentario= binding.mtComentarioAniadir.text.toString()
        if(!comprobarCampo())return
        db= FirebaseDatabase.getInstance("https://proyectofinal-de3bf-default-rtdb.europe-west1.firebasedatabase.app/")
        var datosValoraciones=DatosValoraciones(email,comentario)
        reference=db.getReference("comentarios")
        reference.child(pref.leerNomGrupo().toString()).setValue(datosValoraciones).addOnSuccessListener {
            limpiar()
        }.addOnFailureListener {
            Toast.makeText(this,"ERROR-----------------------", Toast.LENGTH_LONG).show()
        }



    }


    private fun limpiar(){
        binding.mtComentarioAniadir.setText("")
    }

    private fun comprobarCampo():Boolean{
        email= pref.leerEmail().toString()
        comentario= binding.mtComentarioAniadir.text.toString()
        if(comentario.length==0){
            binding.mtComentarioAniadir.setError("Rellene con un comentario")
            binding.mtComentarioAniadir.requestFocus()
            return false
        }

        return true
    }


}