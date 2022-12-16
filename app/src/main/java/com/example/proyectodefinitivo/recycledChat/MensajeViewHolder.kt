package com.example.proyectodefinitivo.recycledChat

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectodefinitivo.R
import com.example.proyectodefinitivo.databinding.MensajeLayoutBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

class MensajeViewHolder(v: View):RecyclerView.ViewHolder(v) {
    private val binding=MensajeLayoutBinding.bind(v)
    lateinit var storageReferences: StorageReference

    fun render(mensaje:Mensaje){
        binding.txtContenido.text=mensaje.msj
        binding.txtEmail.text=mensaje.email
        binding.txtFecha.text=mensaje.fecha

        storageReferences=Firebase.storage.reference
        var imag=storageReferences.child(mensaje.imagen)
        if (imag!=null){
            imag.downloadUrl
                .addOnSuccessListener {
                    Picasso.get().load(it).resize(300,300).centerCrop().into(binding.imgusuario)
                }
                .addOnFailureListener {
                    binding.imgusuario.setImageDrawable(
                        ContextCompat.getDrawable(
                            itemView.context,
                            R.drawable.img
                        )
                    )
                }
        }

    }


}