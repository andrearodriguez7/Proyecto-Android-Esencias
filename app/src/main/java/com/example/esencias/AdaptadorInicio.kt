package com.example.esencias

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


class AdaptadorInicio (
    private val listaVelas: List<Vela>,
    private val onItemClick: (Vela) -> Unit
): RecyclerView.Adapter<AdaptadorInicio.ViewHolder>() {

    /*
     * Clase interna ViewHolder que se encarga de gestionar las vistas individuales
     * de cada elemento del RecyclerView.
     * */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.nombreVela)
        val imagen: ImageView = itemView.findViewById(R.id.imagenVela)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vela_inicio, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listaVelas[position]
        holder.nombre.text = item.nombre

        Glide.with(holder.imagen.context)
            .load(item.imagen) // Aquí cargamos la imagen desde la base de datos
            .apply(RequestOptions()
                .transform(CenterCrop(), RoundedCorners(20)) // Primero se aplica centerCrop, luego el borde redondeado
            )
            .into(holder.imagen)

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return listaVelas.size
    }
}
