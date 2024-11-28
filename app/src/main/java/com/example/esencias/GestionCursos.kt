package com.example.esencias

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.replace
import com.skydoves.expandablelayout.ExpandableLayout


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class GestionCursos : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_gestion_cursos, container, false)
        val expandableLayout = view.findViewById<ExpandableLayout>(R.id.expandableLayout)
        val expandableLayout2 = view.findViewById<ExpandableLayout>(R.id.expandableLayout2)
        val expandableLayout3 = view.findViewById<ExpandableLayout>(R.id.expandableLayout3)
        val flechaVolver = view.findViewById<ImageView>(R.id.FlechaVolverCursos)

        val botonInsertar= view.findViewById<Button>(R.id.InsertarButtonCursos)
        val botonEliminar= view.findViewById<Button>(R.id.EliminarButtonCursos)
        val botonModificar= view.findViewById<Button>(R.id.ModificarButtonCursos)

        flechaVolver.setOnClickListener{
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_esencias,MenuAdministrador())
                .commit()
        }

        botonInsertar.setOnClickListener{
            val db=BBDD(requireContext())
            val nombreInsert= view.findViewById<EditText>(R.id.InsertarNombreCurso).text.toString()
            val descripcionInsert= view.findViewById<EditText>(R.id.InsertarDescripcionCursos).text.toString()
            val informacionInsert= view.findViewById<EditText>(R.id.InsertarInformacionCursos).text.toString()
            val precioInsert= view.findViewById<EditText>(R.id.InsertarPrecioCursos).text.toString()
            val urlInsert= view.findViewById<EditText>(R.id.InsertarUrlCursos).text.toString()
            db.insertarCurso(nombreInsert,precioInsert.toDouble(),descripcionInsert,informacionInsert,urlInsert,
                0,0)
        }

        botonEliminar.setOnClickListener{
            val db=BBDD(requireContext())
            val codigo= view.findViewById<EditText>(R.id.EliminarCodigoCurso).text.toString()
            db.eliminarCurso(codigo)
        }
        botonModificar.setOnClickListener{
            val db=BBDD(requireContext())
            val codigoModificar= view.findViewById<EditText>(R.id.ModificarCodigoCursos).text.toString()
            val nombreModificar= view.findViewById<EditText>(R.id.ModificarNombreCurso).text.toString()
            var precioModificar= view.findViewById<EditText>(R.id.ModificarPrecioCursos).text.toString()
            val descripcionModificar= view.findViewById<EditText>(R.id.ModificarDescripcionCursos).text.toString()
            val informacionModificar= view.findViewById<EditText>(R.id.ModificarInformacionCursos).text.toString()
            val imagenModificar= view.findViewById<EditText>(R.id.ModificarUrlCursos).text.toString()
            //val tamanoModificar= view.findViewById<EditText>(R.id.ModificarTamanoCursos).text.toString()
            //val tamanoModificar= view.findViewById<EditText>(R.id.ModificarTamanoCursos).text.toString()

            if(precioModificar=="") precioModificar="-1.0"

            db.modificarCurso(codigoModificar,nombreModificar,precioModificar.toDouble(),descripcionModificar,informacionModificar,imagenModificar,0,0)
        }

        expandableLayout.setOnClickListener {
            if (expandableLayout.isExpanded) {
                expandableLayout.collapse()
            } else {
                expandableLayout.expand()
            }
        }
        expandableLayout2.setOnClickListener {
            if (expandableLayout2.isExpanded) {
                expandableLayout2.collapse()
            } else {
                expandableLayout2.expand()
            }
        }
        expandableLayout3.setOnClickListener {
            if (expandableLayout3.isExpanded) {
                expandableLayout3.collapse()
            } else {
                expandableLayout3.expand()
            }
        }
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GestionVelas().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}