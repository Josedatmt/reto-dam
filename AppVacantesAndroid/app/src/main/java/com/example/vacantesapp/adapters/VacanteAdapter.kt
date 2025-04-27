package com.example.vacantesapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_vacantes_android.databinding.ItemVacanteBinding

import com.example.vacantesapp.models.Vacante

class VacanteAdapter(private var vacantes: List<Vacante>) : RecyclerView.Adapter<VacanteAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemVacanteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemVacanteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val vacante = vacantes[position]
        holder.binding.tvTitulo.text = vacante.titulo
        holder.binding.tvEmpresa.text = vacante.empresa.nombre
        holder.binding.tvDescripcion.text = vacante.descripcion
    }

    override fun getItemCount() = vacantes.size

    fun updateData(newVacantes: List<Vacante>) {
        vacantes = newVacantes
        notifyDataSetChanged()
    }
}