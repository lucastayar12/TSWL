package com.example.tswl.telas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.tswl.R
import com.example.tswl.model.Usuario
import com.example.tswl.repository.DAO_Usuarios

class Cadastrar : AppCompatActivity() {

    lateinit var etLogin : EditText
    lateinit var etSenha : EditText
    lateinit var daoUsuario: DAO_Usuarios

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar)

        etLogin = findViewById(R.id.et_LoginC)
        etSenha = findViewById(R.id.et_SenhaC)

        daoUsuario = DAO_Usuarios()

    }

    fun cadastrarSe(view: View){
        if (etLogin.text.isEmpty()){
            Toast.makeText(this, "Digite um usuario válido para login!", Toast.LENGTH_SHORT).show()
            etLogin.requestFocus()
        }

        if (etSenha.text.isEmpty()){
            Toast.makeText(this, "Digite uma senha válida!", Toast.LENGTH_SHORT).show()
            etSenha.requestFocus()
        }

        if (etSenha.text.isNotEmpty() && etLogin.text.isNotEmpty()){
            var usuario = Usuario(daoUsuario.getIndex(), etLogin.text.toString(), etSenha.text.toString())
            daoUsuario.criarUsuario(usuario)
            finish()
        }

    }


    fun voltar(View : View){ finish() }
}