package com.example.vacantesapp.admin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_vacantes_android.databinding.ActivityAdminEmpresasBinding
import com.example.vacantesapp.adapters.EmpresaAdapter
import com.example.vacantesapp.api.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AdminEmpresasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminEmpresasBinding
    private lateinit var adapter: EmpresaAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminEmpresasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        loadEmpresas()
    }
    private fun setupRecyclerView() {
        adapter = EmpresaAdapter(emptyList())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
    private fun loadEmpresas() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClient.instance.getEmpresas()
                if (response.isSuccessful) {
                    val empresas = response.body() ?: emptyList()
                    runOnUiThread {
                        adapter.updateData(empresas)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}