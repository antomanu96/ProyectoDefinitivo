package com.example.proyectodefinitivo

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectodefinitivo.databinding.ActivityHistorialBinding
import com.example.proyectodefinitivo.databinding.ActivityMasInfoBinding
import com.example.proyectodefinitivo.recycled.DatosBuscados
import com.example.proyectodefinitivo.recycled.HistorialAdapter
import com.example.proyectodefinitivo.recycledValoraciones.DatosValoraciones
import com.google.firebase.database.*

class HistorialActivity : AppCompatActivity() {
    lateinit var binding: ActivityHistorialBinding
    lateinit var pref:Preferencias

    private lateinit var db:FirebaseDatabase
    private lateinit var reference: DatabaseReference
    var lista= mutableListOf<DatosBuscados>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHistorialBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pref= Preferencias(this)
        setRecycler()
        traerArticulos()
        setListener()
    }

    private fun setListener() {
        binding.btnVolverH.setOnClickListener {
            finish()
        }
    }

    private fun traerArticulos() {
        db= FirebaseDatabase.getInstance("https://proyectofinal-de3bf-default-rtdb.europe-west1.firebasedatabase.app/")
        reference=db.getReference("historial")
        reference.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                lista.clear()

                if (snapshot.exists()){
                    for (item in snapshot.children){
                        val historial =item.getValue(DatosBuscados::class.java)
                        if(historial!=null){
                            lista.add(historial)
                        }
                    }
                    setRecycler()
                    binding.recView.adapter=HistorialAdapter(lista)
                }
                else{
                    setRecycler()
                    binding.recView.adapter=HistorialAdapter(lista)
                }
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    private fun setRecycler() {
        binding.recView.layoutManager=LinearLayoutManager(this)
        binding.recView.setHasFixedSize(true)
    }
}