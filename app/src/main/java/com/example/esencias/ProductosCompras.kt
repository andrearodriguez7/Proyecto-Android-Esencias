package com.example.esencias

data class ProductosCompras(
    val idProducto: Int,
    val idCesta: Int,
    val nombre: String,
    val precio: Double,
    val imagen: String,
    var cantidad: Int
)

