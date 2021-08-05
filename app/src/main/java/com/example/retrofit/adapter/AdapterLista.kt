package com.example.retrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.R
import com.example.retrofit.endpoint.ServiceCar
import com.example.retrofit.model.Marca
import java.util.Collections.addAll

class AdapterMarcas : RecyclerView.Adapter<ViewHolderItemMarca>() {

    private var listDeMarcas: MutableList<Marca> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItemMarca {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_main, parent, false)
        return ViewHolderItemMarca(itemView)
    }


    override fun onBindViewHolder(holder: ViewHolderItemMarca, position: Int) {
        listDeMarcas[position].apply {
            holder.bind(this)
        }
    }


    override fun getItemCount(): Int {
        return listDeMarcas.size
    }

    fun update(newList: List<Marca>) {
        listDeMarcas = mutableListOf()
        listDeMarcas.addAll(newList)
        notifyDataSetChanged()
    }

}

class ViewHolderItemMarca(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val codeTextView = itemView.findViewById<TextView>(R.id.codeTextView)
    val nameTextView = itemView.findViewById<TextView>(R.id.nameTextView)

    fun bind(marca: Marca) {
        codeTextView.text = marca.id
        nameTextView.text = marca.name
    }
}