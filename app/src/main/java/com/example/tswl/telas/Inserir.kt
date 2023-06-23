package com.example.tswl.telas

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.annotation.RequiresApi
import com.example.tswl.R
import com.example.tswl.model.Beneficiario
import com.example.tswl.repository.DAO_Beneficiario
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class Inserir : AppCompatActivity() {
    lateinit var et_Pseudonimo: EditText
    lateinit var et_Descricao: EditText
    lateinit var daoBeneficiario: DAO_Beneficiario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inserir)
        et_Pseudonimo = findViewById(R.id.et_Pseudonimo)
        et_Descricao = findViewById(R.id.et_Descricao)

        daoBeneficiario = DAO_Beneficiario(this)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun inserirBeneficiario(View: View) {
        var listBeneficios = ArrayList<String>()
        listBeneficios.add(
            ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"))
                .format(DateTimeFormatter.ofPattern("dd/MM/yyy hh:mm:ss a"))
        )

        val beneficiario = Beneficiario(
            daoBeneficiario.getIndex(),
            et_Pseudonimo.text.toString(),
            et_Descricao.text.toString(),
            ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"))
                .format(DateTimeFormatter.ofPattern("dd/MM/yyy hh:mm:ss a")),
            listBeneficios
        )
        daoBeneficiario.criarBeneficiario(beneficiario)
        et_Pseudonimo.text.clear()
        et_Descricao.text.clear()
    }

    fun voltarInicio(View: View) {
        this.finish()
    }

}