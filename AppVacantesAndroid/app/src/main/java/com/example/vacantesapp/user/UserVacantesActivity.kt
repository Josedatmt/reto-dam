package com.example.vacantesapp.user
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_vacantes_android.databinding.ActivityUserVacantesBinding
import com.example.vacantesapp.adapters.VacanteAdapter
import com.example.vacantesapp.api.RetrofitClient

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
class UserVacantesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserVacantesBinding
    private lateinit var adapter: VacanteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserVacantesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        loadVacantes()
    }
    private fun setupRecyclerView() {
        adapter = VacanteAdapter(emptyList())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
    private fun loadVacantes() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClient.instance.getVacantes()
                if (response.isSuccessful) {
                    val vacantes = response.body() ?: emptyList()
                    runOnUiThread {
                        adapter.updateData(vacantes)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}