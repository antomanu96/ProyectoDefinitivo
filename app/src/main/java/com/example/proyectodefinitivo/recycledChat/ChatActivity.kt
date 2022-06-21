package com.example.proyectodefinitivo.recycledChat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectodefinitivo.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {
    lateinit var binding: ActivityChatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }




}