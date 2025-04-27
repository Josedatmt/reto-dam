package com.example.vacantesapp.auth
import androidx.lifecycle.ViewModel
import com.example.vacantesapp.api.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.vacantesapp.models.LoginRequest

@HiltViewModel
class AuthViewModel @Inject constructor(private val api: ApiService) : ViewModel() {
    suspend fun login(email: String, password: String): Boolean {
        return try {
            val request = LoginRequest(email, password)
            val response = api.login(request)
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }
}