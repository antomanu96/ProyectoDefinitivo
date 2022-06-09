package com.example.proyectodefinitivo

import android.R.string
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectodefinitivo.databinding.ActivityBuscarGruposBinding
import com.example.proyectodefinitivo.databinding.ActivityMasInfoBinding


class MasInfoActivity : AppCompatActivity() {
    lateinit var binding:ActivityMasInfoBinding
    val BASE_URL="https://www.todomusica.org/"

    //https://www.todomusica.org/the_killers/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMasInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setWebView()
        setListener()
    }

    private fun setListener() {
        binding.btnVolverMInfo.setOnClickListener {
            startActivity(Intent(this,ActivityBuscarGruposBinding::class.java))
        }
    }


    private fun setWebView() {
        binding.webView.webChromeClient = object : WebChromeClient(){

        }
        binding.webView.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                //return super.shouldOverrideUrlLoading(view, request)
                return false
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                binding.swipeRefresh.isRefreshing=true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.swipeRefresh.isRefreshing=false
            }
        }
        //activamos javascript, por defecto desactivado
        val settings = binding.webView.settings
        settings.javaScriptEnabled = true
        //------

        //obtenemos el grupo buscado en el Active BusquedaGrupos y lo añadimos a la url
        var budle =intent.extras
        var pref=Preferencias(this)
        var grupo=pref.leerNomGrupo()

        grupo=funcionReemplazarEspacios(grupo)

        binding.webView.loadUrl("$BASE_URL$grupo/")
        println("$BASE_URL$grupo/")


    }

    //----------------------------------------------------------------

    override fun onBackPressed() {
        //super.onBackPressed()
        //para poder ir navegando por todas las páginas vistas
        if(binding.webView.canGoBack()){
            binding.webView.goBack()
        }else{
            super.onBackPressed()
        }
    }

    //----------------------------------------------------------------

    //metodo para sustituir espacios por barras bajas
    fun funcionReemplazarEspacios(cadena: String?): String {
        if (cadena != null) {
            return cadena.replace(" ", "_",ignoreCase=true)
        }else{
            return ""
        }
    }

    

}




