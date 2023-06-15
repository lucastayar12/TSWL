package com.example.tswl.model

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tswl.R

class BeneficiarioAdapter(
    private val beneficiarios: List<Beneficiario>,
    val context: Context,
    val intent: Intent
) : RecyclerView.Adapter<BeneficiarioAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnAtualizar: Button = itemView.findViewById(R.id.btnAtualizar)
        val codigoBeneficiario: TextView = itemView.findViewById(R.id.tv_Codigo)
        val pseudonimoBeneficiario: TextView = itemView.findViewById(R.id.tv_Pseudonimo)
        val descricaoBeneficiario: TextView = itemView.findViewById(R.id.tv_Descricao)
        val statusBeneficiario: TextView = itemView.findViewById(R.id.tv_Status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

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
    }

    override fun getItemCount(): Int {
        return beneficiarios.size
    }

}