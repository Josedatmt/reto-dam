package com.example.vacantesapp.api
import com.example.vacantesapp.models.*
import com.example.vacantesapp.models.LoginRequest
import retrofit2.Response
import retrofit2.http.*
interface ApiService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<User>
    @GET("vacantes")
    suspend fun getVacantes(): Response<List<Vacante>>
    @GET("empresas")
    suspend fun getEmpresas(): Response<List<Empresa>>
    @GET("categorias")
    suspend fun getCategorias(): Response<List<Categoria>>
    @POST("solicitudes")
    suspend fun createSolicitud(@Body request: SolicitudRequest): Response<Solicitud>
}
data class LoginRequest(val email: String, val password: String)
data class SolicitudRequest(val vacanteId: String, val usuarioId: String, val archivo: String)