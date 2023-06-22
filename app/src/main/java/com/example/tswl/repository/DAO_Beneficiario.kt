package com.example.tswl.repository

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.tswl.model.Beneficiario
import com.example.tswl.model.BeneficiarioAdapter
import com.example.tswl.telas.Atualizar
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.*
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class DAO_Beneficiario(context: Context) {
    var banco: DatabaseReference
    var listaBeneficiarios = ArrayList<Beneficiario>()
    var context: Context
    private var index: Int

    init {
        this.banco = Firebase.database.reference
        this.context = context
        this.index = getIndex()
    }

    fun criarBeneficiario(beneficiario: Beneficiario) {
        this.banco.child("Beneficiarios").child(beneficiario.codigo.toString())
            .setValue(beneficiario)
        this.banco.child("idSeqBeneficiarios").setValue(getIndex() + 1)
    }

    fun lerBeneficiario(recyclerView: RecyclerView) {
        banco.child("Beneficiarios").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    listaBeneficiarios.clear()
                    val gson = Gson()
                    for (i in snapshot.children) {
                        val json = gson.toJson(i.value)
                        val beneficiario = gson.fromJson(json, Beneficiario::class.java)
                        listaBeneficiarios.add(
                            Beneficiario(
                                beneficiario.codigo,
                                beneficiario.pseudonimo,
                                beneficiario.descricao,
                                beneficiario.dataInicio,
                                beneficiario.dataBeneficios,
                                beneficiario.status
                            )
                        )
                        insereBeneficiarioRecyclerView(recyclerView, listaBeneficiarios)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    fun retornaListaBeneficiario(): ArrayList<Beneficiario> {
        return listaBeneficiarios
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun darBeneficio(beneficiario: Beneficiario) {
        val beneficiarioRef = banco.child("Beneficiarios")
        beneficiarioRef.child(beneficiario.codigo.toString()).setValue(beneficiario)
    }

    fun atualizarBeneficiario(beneficiario: Beneficiario) {
        val beneficiarioRef = banco.child("Beneficiarios")
        beneficiarioRef.child(beneficiario.codigo.toString()).setValue(beneficiario)
    }

    fun getIndex(): Int {
        banco.child("idSeqBeneficiarios").addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                index = snapshot.value.toString().toInt()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        return index
    }

    fun insereBeneficiarioRecyclerView(
        recyclerView: RecyclerView,
        listaBeneficiario: ArrayList<Beneficiario>
    ) {
        recyclerView.adapter = BeneficiarioAdapter(
            listaBeneficiario,
            this.context,
            Intent(context, Atualizar::class.java)
        )
    }
}