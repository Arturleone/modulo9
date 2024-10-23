package com.example.modulo9

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ComplaintsAdapter(private var complaints: List<Complaint>, private val onItemAction: (Complaint, String) -> Unit) : RecyclerView.Adapter<ComplaintsAdapter.ComplaintViewHolder>() {

    class ComplaintViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.tvComplaintTitle)
        val description: TextView = view.findViewById(R.id.tvComplaintDescription)
        val editButton: ImageView = view.findViewById(R.id.ivEdit)
        val deleteButton: ImageView = view.findViewById(R.id.ivDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComplaintViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_complaint, parent, false)
        return ComplaintViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComplaintViewHolder, position: Int) {
        val complaint = complaints[position]
        holder.title.text = complaint.Titulo
        holder.description.text = complaint.Descricao
        holder.title.setTextColor(getColorForTitle(complaint.Titulo))

        holder.editButton.setOnClickListener { onItemAction(complaint, "edit") }
        holder.deleteButton.setOnClickListener { onItemAction(complaint, "delete") }

        // Lógica para mostrar/hide ícones de edição/exclusão (com base no longo toque)
        // (Implementar lógica para controlar visibilidade)
    }

    override fun getItemCount(): Int = complaints.size

    fun updateData(newComplaints: List<Complaint>) {
        complaints = newComplaints
        notifyDataSetChanged()
    }

    fun filter(query: String?) {
        val filteredList = if (query.isNullOrEmpty()) {
            complaints // Retornar lista original
        } else {
            complaints.filter { it.Titulo.contains(query, ignoreCase = true) }
        }
        updateData(filteredList)
    }

    private fun getColorForTitle(title: String): Int {
        return when (title) {
            "Cobrança" -> 0xFF00FF00.toInt() // Verde
            "Performance" -> 0xFFFFA500.toInt() // Laranja
            "Serviços" -> 0xFF0000FF.toInt() // Azul
            "Usabilidade" -> 0xFF800080.toInt() // Roxo
            else -> 0xFF000000.toInt() // Preto padrão
        }
    }
}
