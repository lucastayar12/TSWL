package com.example.tswl.telas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.tswl.R
import com.example.tswl.model.Usuario
import com.example.tswl.repository.DAO_Usuarios

class Login : AppCompatActivity() {

    lateinit var etLogin: EditText
    lateinit var etSenha : EditText
    lateinit var  daoUsuarios: DAO_Usuarios


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etLogin = findViewById(R.id.et_Login)
        etSenha = findViewById(R.id.et_Senha)

        daoUsuarios = DAO_Usuarios()

    }

    fun telaCadastro(view : View){ startActivity(Intent(this, Cadastrar::class.java)) }

    fun tryLogin(view: View){
        var user = Usuario(0, etLogin.text.toString(), etSenha.text.toString())
        var logged = daoUsuarios.login(user)

        if (logged)
            startActivity(Intent(this, MainActivity::class.java))
        else
            Toast.makeText(this, "Erro na autenticação!", Toast.LENGTH_SHORT).show()
    }
}