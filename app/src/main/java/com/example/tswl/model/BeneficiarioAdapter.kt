package com.example.tswl.model

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.tswl.R
import com.example.tswl.repository.DAO_Beneficiario
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class BeneficiarioAdapter(
    private val beneficiarios: List<Beneficiario>,
    val context: Context,
    val intent: Intent
) : RecyclerView.Adapter<BeneficiarioAdapter.ViewHolder>() {

    val daoBeneficiario = DAO_Beneficiario(context)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnDarBeneficio : Button = itemView.findViewById(R.id.btnDarBeneficio)
        val btnAtualizar: Button = itemView.findViewById(R.id.btnAtualizar)
        val btnInativar: Button = itemView.findViewById(R.id.btn_Inativar)
        val codigoBeneficiario: TextView = itemView.findViewById(R.id.tv_Codigo)
        val pseudonimoBeneficiario: TextView = itemView.findViewById(R.id.tv_Pseudonimo)
        val descricaoBeneficiario: TextView = itemView.findViewById(R.id.tv_Descricao)
        val statusBeneficiario: TextView = itemView.findViewById(R.id.tv_Status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = beneficiarios[position]
        holder.codigoBeneficiario.text = item.codigo.toString()
        holder.pseudonimoBeneficiario.text = item.pseudonimo
        holder.descricaoBeneficiario.text = "Descrição: " + item.descricao
        holder.statusBeneficiario.text = "Status: " + item.status

        var rv_Bene = (context as Activity).findViewById(R.id.rv_Beneficiarios) as RecyclerView

        holder.btnAtualizar.setOnClickListener {
            context.startActivity(intent.putExtra("beneficiario", item))
        }

        holder.btnDarBeneficio.setOnClickListener{
            item.dataBeneficios.add(
                ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"))
                    .format(DateTimeFormatter.ofPattern("MM.dd.yyy hh.mm.ss a"))
            )

            daoBeneficiario.darBeneficio(item)
        }

        holder.btnInativar.setOnClickListener{
            item.status = "Inativo"
            daoBeneficiario.atualizarBeneficiario(item)
            daoBeneficiario.lerBeneficiario(rv_Bene)
        }
    }

    override fun getItemCount(): Int {
        return beneficiarios.size
    }



}