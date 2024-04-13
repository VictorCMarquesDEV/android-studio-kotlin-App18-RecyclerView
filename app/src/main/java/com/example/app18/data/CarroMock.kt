package com.example.app18.data

import com.example.app18.model.Carro

class CarroMock {
    var listaCarros = ArrayList<Carro>()

    init {
        for (i in 0..5) {
            listaCarros.add(Carro(i, i.toString()))
        }
    }
}