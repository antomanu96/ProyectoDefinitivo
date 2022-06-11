package com.example.proyectodefinitivo.recycled

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectodefinitivo.databinding.HistorialLayoutBinding
import com.example.proyectodefinitivo.recycledValoraciones.DatosValoraciones

class HistorialViewHolder(v: View):RecyclerView.ViewHolder(v) {
    val binding=HistorialLayoutBinding.bind(v)

    fun render(historial:DatosBuscados){
        binding.tvNombre.text=historial.nomGrupo
        binding.tvUsuario.text=historial.email
    }
}