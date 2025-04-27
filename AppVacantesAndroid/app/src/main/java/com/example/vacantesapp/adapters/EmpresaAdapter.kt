package com.example.vacantesapp.adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_vacantes_android.databinding.ItemEmpresaBinding

import com.example.vacantesapp.models.Empresa
class EmpresaAdapter(private var empresas: List<Empresa>) : RecyclerView.Adapter<EmpresaAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemEmpresaBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemEmpresaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val empresa = empresas[position]
        holder.binding.tvNombre.text = empresa.nombre
        holder.binding.tvDireccion.text = empresa.direccion
        holder.binding.tvPais.text = empresa.pais
    }
    override fun getItemCount() = empresas.size
    fun updateData(newEmpresas: List<Empresa>) {
        empresas = newEmpresas
        notifyDataSetChanged()
    }
}