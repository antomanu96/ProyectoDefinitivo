package com.example.proyectodefinitivo.recycledChat

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.proyectodefinitivo.BuscarGruposActivity
import com.example.proyectodefinitivo.MainActivity
import com.example.proyectodefinitivo.Preferencias
import com.example.proyectodefinitivo.R
import com.example.proyectodefinitivo.databinding.ActivityFotoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.squareup.picasso.Picasso

class FotoActivity : AppCompatActivity() {


    private val responseLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode== RESULT_OK){
                if (it.data!=null){
                    //hemos seleccionado un archivo
                    val uri: Uri? = it.data!!.data
                    println("La ruta es ========= $uri")
                    var imagenref=storageReferences.child(correo+"/images/default.png")
                    //si quisiera guardar el documento con su nombre
                    //imagenref=storageReferences.child(correo+"/images/${uri?.lastPathSegment}")
                    subirImagenNube(imagenref, uri)
                }
            }
        }

    lateinit var binding: ActivityFotoBinding
    var correo=""
    lateinit var prefs: Preferencias
    lateinit var storageReferences: StorageReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFotoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title="Cambiar foto"
        prefs= Preferencias(this)
        storageReferences = Firebase.storage.reference
        cogerDatos()
        setUp()
        cargarFoto()
    }

    private fun cogerDatos() {
        //val bundle=intent.extras
        correo= prefs.leerEmail().toString()

        binding.txtEmail.text=correo
    }
    private fun cargarFoto() {
        var imagenRef = storageReferences.child(correo+"/images/default.png")
        if (imagenRef!=null){
            imagenRef.downloadUrl
                .addOnSuccessListener {
                    //Existe la imagen de perfil la cargo
                    Picasso.get().load(it).resize(300,300).centerCrop().into(binding.imgPrefil)
                }
                .addOnFailureListener {
                    //No exixte la imagen de perfil pongo noimg.png
                    binding.imgPrefil.setImageDrawable(getDrawable(R.drawable.imagusuariopred))
                }
        }
    }

    private fun cambiarFoto() {
        //ABRIMOS EN NAVEGADOR DE ARCHIVOS
        var i= Intent(Intent.ACTION_OPEN_DOCUMENT)
        i.addCategory(Intent.CATEGORY_OPENABLE)
        i.setType("image/*")
        responseLauncher.launch(i)
    }

    private fun cerrarSesion() {
        FirebaseAuth.getInstance().signOut()
        prefs.borrarTodo()
        var intent=Intent(this,MainActivity::class.java)

        startActivity(intent)
    }

    private fun setUp() {
        binding.btnImagen.setOnClickListener {
            cambiarFoto()
        }
        binding.btnLogOut.setOnClickListener {
            cerrarSesion()
        }
        binding.btnSalir.setOnClickListener {
            finishAffinity()
            System.exit(0)
        }

        binding.btnVolverFoto.setOnClickListener {
            var intent=Intent(this,ChatActivity::class.java)

            startActivity(intent)
        }
    }
    private fun subirImagenNube(imagenref: StorageReference, rutaArchivo: Uri?) {
        if (rutaArchivo!=null){
            imagenref.putFile(rutaArchivo)
                .addOnCompleteListener(){
                    if (it.isSuccessful){
                        Toast.makeText(this, "Imagend e perfil subida con exito.", Toast.LENGTH_LONG).show()
                        cargarFoto()
                    }else{
                        Toast.makeText(this, "Error al guardar la Imagen", Toast.LENGTH_LONG).show()
                        Log.d("Error de subida ==============", it.exception.toString())
                    }
                }
        }
    }
}