package com.example.proyectodefinitivo.recycledChat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectodefinitivo.R

class MensajeAdapter(private val lista:ArrayList<Mensaje>):RecyclerView.Adapter<MensajeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MensajeViewHolder {
        val inflate =LayoutInflater.from(parent.context)
        val v=inflate.inflate(R.layout.mensaje_layout,parent,false)
        return MensajeViewHolder(v)
    }

    override fun onBindViewHolder(holder: MensajeViewHolder, position: Int) {
        val mensaje=lista[position]
        holder.render(mensaje)
    }

    override fun getItemCount(): Int {
        return lista.size
    }
}