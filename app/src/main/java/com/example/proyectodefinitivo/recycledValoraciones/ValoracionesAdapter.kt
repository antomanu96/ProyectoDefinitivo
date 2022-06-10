package com.example.proyectodefinitivo.recycledValoraciones

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectodefinitivo.R

class ValoracionesAdapter (private var lista:List<DatosValoraciones>):RecyclerView.Adapter<ValoracionesViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ValoracionesViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        return ValoracionesViewHolder(layoutInflater.inflate(R.layout.valoraciones_layout,parent,false))
    }

    override fun onBindViewHolder(holder: ValoracionesViewHolder, position: Int) {
        val valoracion=lista[position]
        holder.render(valoracion)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

}
