package com.example.esencias

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class Inicio : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_inicio, container, false)

        val masVelas: ImageView = view.findViewById(R.id.Mas)

        masVelas.setOnClickListener {
            mostrarVelas()
        }

        val infoCurso: ImageView = view.findViewById(R.id.FotoCurso)

        infoCurso.setOnClickListener{
            mostrarInfoCurso()
        }

        return view
    }

    private fun fragmentLoader(fragment:Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun mostrarVelas() {
        val VelasFragment=VelasFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, VelasFragment)
            .commit()
    }

    // Método para mostrar la información del curso

    private fun mostrarInfoCurso() {
        val InformacionCurso=InformacionCurso()
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, InformacionCurso)
            .commit()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Inicio().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}