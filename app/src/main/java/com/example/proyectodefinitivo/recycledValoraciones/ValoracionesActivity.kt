package com.example.proyectodefinitivo.recycledValoraciones

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectodefinitivo.Preferencias
import com.example.proyectodefinitivo.databinding.ActivityValoracionesBinding
import com.google.firebase.database.*

class ValoracionesActivity : AppCompatActivity() {
    lateinit var binding: ActivityValoracionesBinding

    lateinit var pref: Preferencias
    private lateinit var db: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    var coment=""
    var emailC=""
    var lista= mutableListOf<DatosValoraciones>()



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
            startActivity(Intent(this,AniadirComentarioActivity::class.java))
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
        //nomGruo=pref.leerNomGrupo().toString()
        //println("-----------------------------"+nomGruo)
        reference= db.getReference("comentarios")
            //.push().child(pref.leerNomGrupo().toString())
            //.child(pref.leerNomGrupo().toString())


        reference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                lista.clear()
                if(snapshot.exists()){
                    for (item in snapshot.children){
                        //val comentararios=item.getValue(DatosValoraciones)
                        //var datosValoraciones=DatosValoraciones(email,comentario)
                        emailC=item.child("email").getValue().toString()
                        coment=item.child("comentario").getValue().toString()
                        var comentarios=DatosValoraciones(emailC, coment)
                        //val comentarios=item.getValue(DatosValoraciones::class.java)
                        if (comentarios != null) {
                            print("email---------"+emailC)
                            print("comentario--------"+coment)
                        }

                        if (comentarios!=null){
                            lista.add(comentarios)
                        }
                    }
                    setRecycled()
                    binding.recViewVal.adapter=ValoracionesAdapter(lista)
                }else{
                    setRecycled()
                    binding.recViewVal.adapter=ValoracionesAdapter(lista)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }

}