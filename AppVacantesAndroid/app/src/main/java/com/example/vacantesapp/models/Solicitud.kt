package com.example.vacantesapp.models
import java.util.Date
data class Solicitud(
    val id: String,
    val fecha: Date,
    val estado: String,
    val vacante: Vacante,
    val usuario: User,
    val archivo: String
)