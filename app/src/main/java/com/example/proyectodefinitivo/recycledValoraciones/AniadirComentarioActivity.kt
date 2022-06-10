package com.example.proyectodefinitivo.recycledValoraciones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectodefinitivo.Preferencias
import com.example.proyectodefinitivo.R
import com.example.proyectodefinitivo.databinding.ActivityAniadirComentarioBinding

class AniadirComentarioActivity : AppCompatActivity() {
    lateinit var binding: ActivityAniadirComentarioBinding
    lateinit var pref:Preferencias
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
            
        }
    }

    private fun guardarComentario(){
        pref= Preferencias(this)
        var email=pref.leerEmail()
        var comentario=binding.mtComentarioAniadir.text


    }


}