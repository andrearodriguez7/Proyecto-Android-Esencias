package com.example.esencias

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AdaptadorPedidos(
    private val context: Context,
    private val listaPedidos: MutableList<Pedido>
) : RecyclerView.Adapter<AdaptadorPedidos.PedidoViewHolder>() {

    inner class PedidoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imagenPedido: ImageView = view.findViewById(R.id.imagenPedido)
        val txtNombre: TextView = view.findViewById(R.id.nombrePedido)
        val txtEstado: TextView = view.findViewById(R.id.estadoPedido)
        val txtFecha: TextView = view.findViewById(R.id.fechaPedido)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cv_pedido, parent, false)
        return PedidoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PedidoViewHolder, position: Int) {
        val pedido = listaPedidos[position]

        // Cargar imagen con Glide (si es una URL o una ruta local)
        Glide.with(context).load(pedido.imagen).into(holder.imagenPedido)

        holder.txtNombre.text = pedido.nombre
        holder.txtEstado.text = "Estado: ${pedido.estado}"
        holder.txtFecha.text = "Fecha estimada: ${pedido.fecha.split(" ")[0]}"
    }

    override fun getItemCount(): Int = listaPedidos.size
}
