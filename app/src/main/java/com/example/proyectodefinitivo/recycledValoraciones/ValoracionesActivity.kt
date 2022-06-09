package com.example.proyectodefinitivo.recycledValoraciones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectodefinitivo.R
import com.example.proyectodefinitivo.databinding.ActivityValoracionesBinding

class ValoracionesActivity : AppCompatActivity() {
    lateinit var binding: ActivityValoracionesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityValoracionesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListener()
    }

    private fun setListener() {
        binding.btnVolverValoraciones.setOnClickListener {
            finish()
        }
    }


}