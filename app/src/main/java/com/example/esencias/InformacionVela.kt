package com.example.esencias

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


private lateinit var vela: Vela

class InformacionVela : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            vela = it.getParcelable("vela") ?: throw IllegalArgumentException("Vela no encontrada")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_informacion_vela, container, false)

        // Referencias a los elementos de la vista

        val imageVela: ImageView = view.findViewById(R.id.imageVela)
        val titleVela: TextView = view.findViewById(R.id.titleVela)
        val descriptionVela: TextView = view.findViewById(R.id.descriptionVela)
        val infoVela: TextView = view.findViewById(R.id.infoVela)
        val botonVelaGrande: Button = view.findViewById(R.id.botonVelaGrande)
        val botonVelaChica: Button = view.findViewById(R.id.botonVelaChica)

        Glide.with(imageVela.context)
            .load(vela.imagen)
            .apply(RequestOptions())
            .into(imageVela)

        titleVela.text=vela.nombre

        descriptionVela.text=vela.descripcion

        infoVela.text=vela.informacion

        // TODO Falta poner la info y funcionalidad de los botones del tamaño de la vela

        view.findViewById<ImageButton>(R.id.volverButton).setOnClickListener{
            fragmentLoader(VelasFragment())
        }

        return view


    }

    private fun fragmentLoader(fragment:Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

}