package com.example.proyectodefinitivo.recycled

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectodefinitivo.R
import com.example.proyectodefinitivo.recycledValoraciones.DatosValoraciones
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class HistorialAdapter(private var lista:List<DatosBuscados>):RecyclerView.Adapter<HistorialViewHolder>() {

    private lateinit var db: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistorialViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        return HistorialViewHolder(layoutInflater.inflate(R.layout.historial_layout,parent,false))
    }

    override fun onBindViewHolder(holder: HistorialViewHolder, position: Int) {
        val historal=lista[position]
        holder.render(historal)
    }

    override fun getItemCount(): Int {
        return lista.size
    }


}