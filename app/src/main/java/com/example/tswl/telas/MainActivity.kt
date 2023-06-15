package com.example.tswl.telas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.tswl.R

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun telaInserir(View : View) { startActivity(Intent(this, Inserir::class.java)) }

    fun telaMostrar(View : View) { startActivity(Intent(this, Mostrar::class.java)) }



}