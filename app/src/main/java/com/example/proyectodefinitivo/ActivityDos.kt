package com.example.proyectodefinitivo

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectodefinitivo.databinding.ActivityDosBinding
import com.google.firebase.auth.FirebaseAuth


class ActivityDos : AppCompatActivity() {
    lateinit var binding: ActivityDosBinding
    lateinit var prefs: Preferencias

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefs= Preferencias(this)
        cogarGuardarEmail()
        setListener()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater=menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_cerrarSesion->{
                cerrarSesion()
            }
            R.id.menu_salir->{
                salir()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setListener() {
        binding.bnBuscar.setOnClickListener {

            var intent=Intent(this,BuscarGruposActivity::class.java)

            startActivity(intent)


        }
        binding.btnHistorial.setOnClickListener {
            var intent=Intent(this,HistorialActivity::class.java)
            startActivity(intent)
        }
    }

    private fun cogarGuardarEmail(){
        var budle=intent.extras
        var email=budle?.getString("EMAIL")
        if (email!=null){
            prefs.guardarEmail(email)
        }
    }


    private fun salir() {
        finishAffinity()
        System.exit(0)
    }

    private fun cerrarSesion() {
        FirebaseAuth.getInstance().signOut()
        val i = Intent(applicationContext, MainActivity::class.java)
        startActivity(i)
        finish()
    }
}