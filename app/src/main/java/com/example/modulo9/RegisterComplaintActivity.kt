package com.example.modulo9

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterComplaintActivity : AppCompatActivity() {

    private lateinit var etTitle: EditText
    private lateinit var etDescription: EditText
    private lateinit var btnSubmit: Button
    private lateinit var btnCancel: Button

    private val api = ApiClient.api

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_complaint)

        etTitle = findViewById(R.id.etTitle)
        etDescription = findViewById(R.id.etDescription)
        btnSubmit = findViewById(R.id.btnSubmit)
        btnCancel = findViewById(R.id.btnCancel)

        // Lógica de submissão
        btnSubmit.setOnClickListener {
            if (etTitle.text.isEmpty() || etDescription.text.isEmpty()) {
                Toast.makeText(this, "Todos os campos são obrigatórios!", Toast.LENGTH_SHORT).show()
            } else {
                submitComplaint()
            }
        }

        // Lógica de cancelar (limpar campos)
        btnCancel.setOnClickListener {
            clearFields()
        }

    }

    private fun submitComplaint() {
        val title = etTitle.text.toString()
        val description = etDescription.text.toString()

        if (!isInternetAvailable()) {
            showNoInternetModal()
            return
        }

        val complaint = Complaint(title, description)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = api.submitComplaint(complaint)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@RegisterComplaintActivity, "boaa", Toast.LENGTH_SHORT).show()
//                        startActivity(Intent(this@RegisterComplaintActivity, ComplaintsListActivity::class.java))
                    } else {
                        // Adicionando logs detalhados
                        Log.e("erro", "Código: ${response.code()}, Mensagem: ${response.message()}, Erro: ${response.errorBody()?.string()}")
                        Toast.makeText(this@RegisterComplaintActivity, "Erro: ${response.code()}, Mensagem: ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@RegisterComplaintActivity, "Falha na conexão!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun isInternetAvailable(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    private fun showNoInternetModal() {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Atenção!")
            .setMessage("Sua conexão com a internet está indisponível! Aguarde.")
            .setCancelable(true)
            .create()
        dialog.show()
    }

    private fun clearFields() {
        etTitle.text.clear()
        etDescription.text.clear()
    }
}
