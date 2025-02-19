package com.example.esencias

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AdaptadorCesta(
    private val context: Context,
    private val listaProductos: MutableList<ProductosCompras>,
    private val db: BBDD,
    private val actualizarTotal: () -> Unit
) : RecyclerView.Adapter<AdaptadorCesta.CestaViewHolder>() {

    inner class CestaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imagenVela: ImageView = view.findViewById(R.id.imagenVela)
        val nombreVela: TextView = view.findViewById(R.id.nombreVela)
        val precioVela: TextView = view.findViewById(R.id.precioVela)
        val txtCantidad: TextView = view.findViewById(R.id.txtCantidad)
        val btnRestar: ImageButton = view.findViewById(R.id.btnRestar)
        val btnSumar: ImageButton = view.findViewById(R.id.btnSumar)
        val btnEliminar: ImageButton = view.findViewById(R.id.btnEliminar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CestaViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cv_cesta, parent, false)

        return CestaViewHolder(view)
    }

    override fun onBindViewHolder(holder: CestaViewHolder, position: Int) {
        val producto = listaProductos[position]

        holder.nombreVela.text = producto.nombre
        holder.precioVela.text = "${producto.precio*producto.cantidad}€"
        holder.txtCantidad.text = producto.cantidad.toString()
        Glide.with(context).load(producto.imagen).into(holder.imagenVela)

        holder.btnSumar.setOnClickListener {
            producto.cantidad++
            db.actualizarCantidadProducto(producto.idCesta, producto.idProducto, producto.cantidad)
            notifyItemChanged(position)
            actualizarTotal()
        }

        holder.btnRestar.setOnClickListener {
            if (producto.cantidad > 1) {
                producto.cantidad--
                db.actualizarCantidadProducto(producto.idCesta, producto.idProducto, producto.cantidad)
                notifyItemChanged(position)
                actualizarTotal()
            }
        }

        holder.btnEliminar.setOnClickListener {
            db.eliminarProducto(producto.idCesta, producto.idProducto)
            listaProductos.removeAt(position)
            notifyItemRemoved(position)
            actualizarTotal()
        }
    }

    override fun getItemCount(): Int = listaProductos.size


}
