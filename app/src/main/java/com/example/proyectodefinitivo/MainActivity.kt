package com.example.proyectodefinitivo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.example.proyectodefinitivo.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity : AppCompatActivity() {

    val responseLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode== RESULT_OK){
            val task =GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try{
                val cuenta= task.getResult(ApiException::class.java)
                if(cuenta!=null){
                    val credenciales= GoogleAuthProvider.getCredential(cuenta.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credenciales).addOnCompleteListener {
                        if (it.isSuccessful){
                            irActivityDos(cuenta.email?:"")
                        }else{
                            mostrarError(it.exception.toString())

                        }
                    }
                }
            }catch (e: ApiException){
                Log.d("ERROR--------------",e.toString())
            }

        }else{
            Log.d("Error----------------","No te devolvio ok ")


        }
    }


    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title="Tu Musica"
        setListener()
    }


    //-------------------------------------------------------------
    private fun setListener() {
        binding.btnGoogle.setOnClickListener {
            loginGoogle()
        }
    }


    //-------------------------------------------------------------
    private fun loginGoogle() {
        //746984170628-4otllruqip6pfaotv8k0g77j3rfrpq09.apps.googleusercontent.com

        val gso= GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("746984170628-4otllruqip6pfaotv8k0g77j3rfrpq09.apps.googleusercontent.com")
            .requestEmail()
            .build()

        val gsc= GoogleSignIn.getClient(this,gso)
        //importante, para que cuando cerremos no tengamos solo q iniciar sesion siempre con la primera cuenta
        //sino que nos deja seleccionar una de las cuentas
        gsc.signOut()
        //
        responseLauncher.launch(gsc.signInIntent)
    }

    //-------------------------------------------------------------
    private fun irActivityDos(email:String) {

        val i = Intent(this,ActivityDos::class.java).apply {
            putExtra("EMAIL",email)
        }
        startActivity(i)
    }

    //-------------------------------------------------------------
    private fun mostrarError(msg: String) {
        val builder= AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage(msg)
            .setPositiveButton("Aceptar",null)
            .create()
            .show()
    }
}