package com.example.proyectodefinitivo.recycledValoraciones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectodefinitivo.DatosBuscados
import com.example.proyectodefinitivo.Preferencias
import com.example.proyectodefinitivo.R
import com.example.proyectodefinitivo.databinding.ActivityValoracionesBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ValoracionesActivity : AppCompatActivity() {
    lateinit var binding: ActivityValoracionesBinding

    lateinit var pref: Preferencias
    private lateinit var db: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    var lista= mutableListOf<DatosBuscados>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityValoracionesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListener()
        setRecycled()
        traerComentario()
    }

    private fun setListener() {
        binding.btnVolverValoraciones.setOnClickListener {
            finish()
        }
        binding.btnHacerValoracion.setOnClickListener {

        }
    }


    var comentarios= listOf<DatosValoraciones>()

    private fun setRecycled(){
        val adapter= ValoracionesAdapter(comentarios)
        binding.recViewVal.layoutManager=LinearLayoutManager(this)
        binding.recViewVal.adapter=adapter
        binding.recViewVal.setHasFixedSize(true)
    }


    fun traerComentario(){
        db= FirebaseDatabase.getInstance("https://proyectofinal-de3bf-default-rtdb.europe-west1.firebasedatabase.app/")
        reference=db.getReference("comentarios")


    }

}