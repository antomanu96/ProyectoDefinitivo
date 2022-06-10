package com.example.proyectodefinitivo

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectodefinitivo.apiArtistas.ApiService
import com.example.proyectodefinitivo.databinding.ActivityBuscarGruposBinding


import com.example.proyectodefinitivo.datos.DatosArtistas
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BuscarGruposActivity: AppCompatActivity(){
    lateinit var binding: ActivityBuscarGruposBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityBuscarGruposBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListener()
    }

    val BASE_URL="https://www.theaudiodb.com/"
    val RESTO_URL="api/v1/json/2/search.php?s="
    val artistaList= mutableListOf<DatosArtistas>()
    //----------------------------------
    private lateinit var db: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    var nomGrupo=""
    var email=""



    //esta funcion es siempre igual
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //---------------------------------------------------------------------------------------

    private fun traerArtista(item:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java)
                .getDatos("$RESTO_URL$item")//para concatenar
            val datos=call.body()
            //para quitar las cabeceras de http tipicas
            runOnUiThread {
                if(call.isSuccessful){
                    val grupo = datos?.grupo?: emptyArray()

                    if(grupo.size>0){
                        //var pref=Preferencias(this)
                            guardarNomGrupo(grupo[0].nombre)

                        binding.tvNombre.text=grupo[0].nombre
                        binding.tvEstilo.text=grupo[0].estilo
                        binding.tvGenero.text=grupo[0].genero
                        binding.tvAniofun.text=grupo[0].anio
                        binding.tvPais.text=grupo[0].pais
                        Picasso.get().load(grupo[0].logo).into(binding.ivGrupo)
                        Picasso.get().load(grupo[0].imagen).into(binding.ivLogo)
                    }

                    //artistaList.add(artista)
                    //render(artista)

                    //TimeUnit.SECONDS.sleep(1L)
                }
                else{
                //error()
                }
            }

        }
    }

    //---------------------------------------------------------------------------------------



    private fun setListener() {
        binding.btnVolver.setOnClickListener {
            //startActivity(Intent(this,ActivityDos::class.java))
            finish()
        }

        binding.btnInfo.setOnClickListener {
            val intent=Intent(this,MasInfoActivity::class.java)
            startActivity(intent)
        }

        binding.btnValoraciones.setOnClickListener {

        }

        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if(!p0.isNullOrEmpty()){
                    var busq=p0.lowercase()
                    ocultarTeclado()
                    //println("Busqueda----------------------------"+busq)
                    traerArtista(busq)

                    //guardarNomGrupo(busq)
                    //intent.putExtra("busqueda",busq)
                    //putExtra("EMAIL",email)
                    crearHistorial()
                }
                return true
            }

            //Cada vez q el usuario haga un cambio en el buscado se llama a este metodo
            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }

        })
    }



    private fun guardarNomGrupo(busq:String){
        var pref=Preferencias(this)
        if(busq!=null){
            pref.guardarNomGrupo(busq)
        }
    }
    //---------------------------------------------------------------------------------------


    private fun error(){
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    //-----------------------------------------------------------------------------------------
    //https://proyectofinal-de3bf-default-rtdb.europe-west1.firebasedatabase.app/

    //metodo que utilizamos para subir la info de historial a nuestro firebase
    private fun crearHistorial(){
        var pref=Preferencias(this)
        nomGrupo=pref.leerNomGrupo().toString()
        println(":::::::---------------------------------"+nomGrupo)

        email=pref.leerEmail().toString()


        db= FirebaseDatabase.getInstance("https://proyectofinal-de3bf-default-rtdb.europe-west1.firebasedatabase.app/")
        var datos=DatosBuscados(nomGrupo, email)
        reference=db.getReference("historial")
        reference.child(nomGrupo).setValue(datos).addOnSuccessListener {
            Toast.makeText(this,"Guardado en el historial", Toast.LENGTH_LONG).show()
        }.addOnFailureListener{
            Toast.makeText(this,it.message.toString(), Toast.LENGTH_LONG).show()
        }



    }

    private fun ocultarTeclado() {
        val imn= getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imn.hideSoftInputFromWindow(binding.contrain.windowToken,0)
    }

    override fun onRestart() {
        super.onRestart()
        var pref=Preferencias(this)
        var busq=pref.leerNomGrupo()
        if(busq!=null){
            ocultarTeclado()
            traerArtista(busq)

        }

    }

}


