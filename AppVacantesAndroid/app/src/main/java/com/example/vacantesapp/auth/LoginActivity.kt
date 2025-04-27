package com.example.vacantesapp.auth

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.example.app_vacantes_android.R
import com.example.app_vacantes_android.databinding.ActivityLoginBinding
import com.example.vacantesapp.admin.AdminUsuariosActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val textView = TextView(this).apply {
            text = "GESTIÓN DE VACANTES"
            textSize = 20f
            setTextColor(ContextCompat.getColor(context, R.color.black))
            gravity = Gravity.CENTER
        }
        val layoutParams = Toolbar.LayoutParams(
            Toolbar.LayoutParams.WRAP_CONTENT,
            Toolbar.LayoutParams.WRAP_CONTENT
        ).apply {
            gravity = Gravity.CENTER
        }
        textView.layoutParams = layoutParams
        binding.toolbar.addView(textView)

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()


            if (email.isNotEmpty() && password.isNotEmpty()) {

                startActivity(Intent(this, AdminUsuariosActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


