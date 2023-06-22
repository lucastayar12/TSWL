package com.example.tswl.telas

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import com.example.tswl.R
import com.example.tswl.model.Beneficiario
import com.example.tswl.repository.DAO_Beneficiario

class Atualizar : AppCompatActivity() {

    lateinit var et_Pseudonimo: EditText
    lateinit var et_Descricao: EditText
    lateinit var daoBeneficiario: DAO_Beneficiario
    lateinit var btn_Atualizar: Button

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atualizar)

        var intent = intent
        var data = intent?.getParcelableExtra<Beneficiario>("beneficiario")

        daoBeneficiario = DAO_Beneficiario(this)

        btn_Atualizar = findViewById(R.id.btn_Atualizar)
        et_Descricao = findViewById(R.id.et_DescricaoAtt)
        et_Pseudonimo = findViewById(R.id.et_PseudonimoAtt)

        btn_Atualizar.setOnClickListener {
            atualizarBeneficiario(data!!, findViewById<View?>(android.R.id.content).rootView)
        }

        et_Pseudonimo.setText(data?.pseudonimo)
        et_Descricao.setText(data?.descricao)
    }

    fun atualizarBeneficiario(beneficiario: Beneficiario, view: View) {
        beneficiario.descricao = et_Descricao.text.toString()
        beneficiario.pseudonimo = et_Pseudonimo.text.toString()
        daoBeneficiario.atualizarBeneficiario(beneficiario)
        voltarInicio(view)
    }

    fun voltarInicio(View: View) {
        this.finish()
    }

}