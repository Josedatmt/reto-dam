package com.example.vacantesapp.admin
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.app_vacantes_android.databinding.ActivityAdminUsuariosBinding

class AdminUsuariosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminUsuariosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminUsuariosBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}