package com.example.remove_ads

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var btnBlockAds: Button
    private lateinit var tvStatus: TextView
    private lateinit var adBlocker: AdBlocker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main))
        { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inializar los componentes del layout
        btnBlockAds = findViewById(R.id.btnBlockAds)
        tvStatus = findViewById(R.id.tvStatus)

        // Crear una instancia del bloqueador de anuncios
        adBlocker = AdBlocker(this)

        // Configurar el boton para bloquear los anuncios
        btnBlockAds.setOnClickListener {
            adBlocker.blockAds()
            tvStatus.text = "Estado: Activo"
        }

    // Iniciar bloqueo de anuncios
       // val adBlocker = AdBlocker(this)
        //adBlocker.blockAds()
    }
}