package com.example.tswl.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Beneficiario(
    var codigo: Int,
    var pseudonimo: String,
    var descricao: String,
    var dataInicio: String,
    var dataBeneficios: ArrayList<String>,
    var status: String = "Ativo"
) : Parcelable {
}