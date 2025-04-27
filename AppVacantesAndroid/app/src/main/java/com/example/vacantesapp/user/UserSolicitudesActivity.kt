package com.example.vacantesapp.user
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.app_vacantes_android.databinding.ActivityUserSolicitudesBinding

class UserSolicitudesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserSolicitudesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserSolicitudesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}