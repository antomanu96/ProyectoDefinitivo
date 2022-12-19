package com.example.proyectodefinitivo.recycledChat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectodefinitivo.*
import com.example.proyectodefinitivo.R
import com.example.proyectodefinitivo.databinding.ActivityChatBinding
import com.google.firebase.database.*

class ChatActivity : AppCompatActivity() {
    lateinit var binding: ActivityChatBinding
    var email=""
    var imagen=""

    private lateinit var db:FirebaseDatabase
    private lateinit var reference:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title="Tu Musica"
        cogerGuardarEmail()
        initDb()
        setlistener()
        ponerListenerDB()
    }

    private fun ponerListenerDB() {
        val postListener =  object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lista : ArrayList<Mensaje> = ArrayList()
                for (data in snapshot.children){
                    val datosMensaje = data.getValue<Mensaje>(Mensaje::class.java)
                    if (datosMensaje!= null){
                        lista.add(datosMensaje)
                    }else continue
                }
                //Ordeno esta lista por marca de tiempo
                lista.sortBy { mensaje -> mensaje.fecha }
                setRecyle(lista)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Error:>>>>>>", error.message.toString())
            }

        }
        reference.addValueEventListener(postListener)
    }

    private fun setRecyle(lista: java.util.ArrayList<Mensaje>) {
        val linearLayoutManager = LinearLayoutManager(this)
        binding.recycle.layoutManager = linearLayoutManager
        binding.recycle.adapter = MensajeAdapter(lista)
        binding.recycle.scrollToPosition(lista.size-1)
    }

    private fun setlistener() {
        binding.ivSend.setOnClickListener {
            enviarMensaje()
        }
    }

    private fun enviarMensaje() {
        var texto = binding.txtMensaje.text.toString().trim()
        if (texto.length==0){
            Toast.makeText(this, "Escribe algo", Toast.LENGTH_LONG).show()
            return
        }


        val time = System.currentTimeMillis()
        imagen=email+"/images/default.png"
        reference.child(time.toString()).setValue(Mensaje(texto, email, imagen))
            .addOnSuccessListener {
                Toast.makeText(this, "Enviado", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener {
                Log.d("Error en envio:>>>>>>", it.message.toString())
            }
        binding.txtMensaje.setText("")

    }

    private fun initDb() {
        db= FirebaseDatabase.getInstance("https://proyectofinal-de3bf-default-rtdb.europe-west1.firebasedatabase.app/")
        reference=db.getReference("chat")
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater=menuInflater.inflate(R.menu.menu2, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.volverChat->{
                var intent=Intent(this, ActivityDos::class.java)

                startActivity(intent)
            }
            R.id.cambiarFoto->{
                var intent=Intent(this, FotoActivity::class.java)

                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun cogerGuardarEmail() {
        var pref= Preferencias(this)
        email= pref.leerEmail().toString()
    }


}