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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private lateinit var vela: Vela

class InformacionVela : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            vela = it.getParcelable("vela") ?: throw IllegalArgumentException("Vela no encontrada")

            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
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

        // Falta poner la info y funcionalidad de los botones del tamaño de la vela

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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InformacionVela.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InformacionVela().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}