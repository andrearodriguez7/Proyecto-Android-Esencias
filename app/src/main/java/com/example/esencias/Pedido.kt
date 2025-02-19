package com.example.esencias

data class Pedido(
    val idPedido: Int,
    val correo: String,
    val estado: String,
    val fecha: String,
    val imagen: String,
    val nombre: String
)
