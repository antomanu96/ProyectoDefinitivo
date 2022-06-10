package com.example.proyectodefinitivo.recycledValoraciones

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.example.proyectodefinitivo.databinding.ValoracionesLayoutBinding

class ValoracionesViewHolder(v:View):RecyclerView.ViewHolder(v) {
    val binding=ValoracionesLayoutBinding.bind(v)


    fun render(coment:DatosValoraciones){
        binding.tvUsuario.text=coment.email
        binding.mtComentario.setText(coment.comentario)
    }
}