package com.example.vacantesapp.user
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.app_vacantes_android.databinding.ActivityUserMainBinding

class UserMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnVacantes.setOnClickListener {
            startActivity(Intent(this, UserVacantesActivity::class.java))
        }
        binding.btnSolicitudes.setOnClickListener {
            startActivity(Intent(this, UserSolicitudesActivity::class.java))
        }
    }
}