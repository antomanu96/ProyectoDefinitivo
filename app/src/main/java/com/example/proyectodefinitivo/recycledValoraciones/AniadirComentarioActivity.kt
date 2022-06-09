package com.example.proyectodefinitivo.recycledValoraciones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectodefinitivo.R
import com.example.proyectodefinitivo.databinding.ActivityAniadirComentarioBinding

class AniadirComentarioActivity : AppCompatActivity() {
    lateinit var binding: ActivityAniadirComentarioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAniadirComentarioBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}