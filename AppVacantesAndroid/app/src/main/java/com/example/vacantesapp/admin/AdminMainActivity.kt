package com.example.vacantesapp.admin
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.app_vacantes_android.databinding.ActivityAdminMainBinding

class AdminMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnVacantes.setOnClickListener {
            startActivity(Intent(this, AdminVacantesActivity::class.java))
        }
        binding.btnEmpresas.setOnClickListener {
            startActivity(Intent(this, AdminEmpresasActivity::class.java))
        }
        binding.btnUsuarios.setOnClickListener {
            startActivity(Intent(this, AdminUsuariosActivity::class.java))
        }
    }
}