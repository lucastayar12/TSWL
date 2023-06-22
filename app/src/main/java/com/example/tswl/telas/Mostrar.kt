package com.example.tswl.telas

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tswl.R
import com.example.tswl.repository.DAO_Beneficiario

class Mostrar : AppCompatActivity() {
    private lateinit var rvBeneficiario: RecyclerView
    lateinit var daoBeneficiario: DAO_Beneficiario
    var flagOnResume : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar)
        rvBeneficiario = findViewById(R.id.rv_Beneficiarios)
        rvBeneficiario.layoutManager = LinearLayoutManager(this)

        daoBeneficiario = DAO_Beneficiario(this)
        daoBeneficiario.lerBeneficiario(rvBeneficiario)
    }

    override fun onResume(){
        super.onResume()
        flagOnResume++
        if(flagOnResume >= 2){
            daoBeneficiario.lerBeneficiario(rvBeneficiario)
            daoBeneficiario.listaBeneficiarios.forEach{
                Log.d("ENTREI", it.pseudonimo)
            }

        }


    }

    fun voltarInicio(View: View) {
        this.finish()
    }

}