package com.example.tswl.repository

import com.example.tswl.model.Usuario
import com.example.tswl.telas.Login
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson

class DAO_Usuarios {
    var banco: DatabaseReference = Firebase.database.reference
    var listUsuarios = ArrayList<Usuario>()
    private var index: Int
    private var logged: Boolean

    init {
        index = getIndex()
        logged = false
    }

    fun criarUsuario(usuario: Usuario) {
        val UsuarioRef = banco.child("Usuarios")
        UsuarioRef.child(usuario.codigo.toString()).setValue(usuario)
    }

    fun getIndex(): Int {
        banco.child("idSeqUsuarios").addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                index = snapshot.value.toString().toInt()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        return index
    }

    fun login(usuario: Usuario): Boolean {
        banco.child("Usuarios").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val gson = Gson()
                for (i in snapshot.children) {
                    val json = gson.toJson(i.value)
                    val usuarioB = gson.fromJson(json, Usuario::class.java)

                    logged = (usuarioB.login == usuario.login) && (usuarioB.senha == usuario.senha)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        return logged
    }

}