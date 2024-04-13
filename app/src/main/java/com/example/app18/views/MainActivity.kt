package com.example.app18.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app18.adapter.CarroListAdapter
import com.example.app18.data.CarroMock
import com.example.app18.databinding.ActivityMainBinding
import com.example.app18.model.Carro

// App18: RecyclerView, mais eficiente que ListView

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CarroListAdapter
    private lateinit var mock: CarroMock
    private var pos = -1

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        mock = CarroMock()
        adapter = CarroListAdapter(mock.listaCarros, CarroListAdapter.OnClickListener {
            // Toast.makeText(this, it.modelo, Toast.LENGTH_SHORT).show()
            pos = returnPos(it.id)
            binding.editModelo.setText(mock.listaCarros[pos].modelo)
        })
        binding.recyclerView.adapter = adapter

        binding.buttonInserir.setOnClickListener {
            val modelo = binding.editModelo.text.toString().toInt()
            mock.listaCarros.add(Carro(modelo, modelo.toString()))
            adapter.notifyDataSetChanged()
        }

        binding.buttonEditar.setOnClickListener {
            if (pos >= 0) {
                mock.listaCarros[pos].modelo = binding.editModelo.text.toString()
                mock.listaCarros[pos].id = binding.editModelo.text.toString().toInt()
                adapter.notifyDataSetChanged()

                pos = -1
            } else {
                Toast.makeText(this, "Edição Inválida", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonEliminar.setOnClickListener {
            if (pos >= 0) {
                mock.listaCarros.removeAt(pos)
                adapter.notifyDataSetChanged()
                pos = -1
            } else {
                Toast.makeText(this, "Eliminação Inválida", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun returnPos(id: Int): Int {
        for (i in 0..mock.listaCarros.size) {
            if (mock.listaCarros[i].id == id) {
                return i
            }
        }
        return -1
    }
}