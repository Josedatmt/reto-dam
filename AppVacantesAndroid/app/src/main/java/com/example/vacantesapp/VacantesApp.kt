package com.example.vacantesapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vacantesapp.auth.LoginActivity

class VacantesApp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        startActivity(Intent(this, LoginActivity::class.java))


        finish()
    }
}
