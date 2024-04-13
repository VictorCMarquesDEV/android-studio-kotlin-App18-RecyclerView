package com.example.app18.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app18.R
import com.example.app18.model.Carro

class CarroListAdapter(val listaCarros: ArrayList<Carro>, val onClickListener: OnClickListener) :
    RecyclerView.Adapter<CarroListAdapter.CarroViewHolder>() {

    private var contadorOnCreate = 0
    private var contadorOnBind = 0

    class CarroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.text_Modelo)
    }

    class OnClickListener(val clickListener: (carro: Carro) -> Unit) {
        fun onClick(carro: Carro) = clickListener(carro)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarroViewHolder {
        contadorOnCreate++
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_carro_list, parent, false)
        return CarroViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaCarros.size
    }

    override fun onBindViewHolder(holder: CarroViewHolder, position: Int) {
        contadorOnBind++
        val carro = listaCarros[position]
        holder.textView.text = carro.modelo
        holder.itemView.setOnClickListener {
            onClickListener.onClick(carro)
        }
    }
}