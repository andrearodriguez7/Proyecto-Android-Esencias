package com.example.esencias

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.skydoves.expandablelayout.ExpandableLayout


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class GestionVelas : Fragment() {

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

        val view = inflater.inflate(R.layout.fragment_gestion_velas, container, false)
        val expandableLayout = view.findViewById<ExpandableLayout>(R.id.expandableLayout)
        val expandableLayout2 = view.findViewById<ExpandableLayout>(R.id.expandableLayout2)
        val expandableLayout3 = view.findViewById<ExpandableLayout>(R.id.expandableLayout3)

        val botonInsertar= view.findViewById<Button>(R.id.InsertarButtonVelas)
        val botonEliminar= view.findViewById<Button>(R.id.EliminarButtonVelas)
        val botonModificar= view.findViewById<Button>(R.id.ModificarButtonVelas)

        botonInsertar.setOnClickListener{
            val db=BBDD(requireContext())
            val nombreInsert= view.findViewById<EditText>(R.id.InsertarNombreVela).text.toString()
            val descripcionInsert= view.findViewById<EditText>(R.id.InsertarDescripcionVelas).text.toString()
            val informacionInsert= view.findViewById<EditText>(R.id.InsertarInformacionVelas).text.toString()
            val precioInsert= view.findViewById<EditText>(R.id.InsertarPrecioVelas).text.toString()
            val urlInsert= view.findViewById<EditText>(R.id.InsertarUrlVelas).text.toString()
            db.insertarVela(nombreInsert,precioInsert.toDouble(),descripcionInsert,informacionInsert,urlInsert,
                "","")
        }

        botonEliminar.setOnClickListener{
            val db=BBDD(requireContext())
            val codigo= view.findViewById<EditText>(R.id.EliminarCodigoVela).text.toString()
            db.eliminarVela(codigo)
        }
        botonModificar.setOnClickListener{
            val db=BBDD(requireContext())
            val codigoModificar= view.findViewById<EditText>(R.id.ModificarCodigoVelas).text.toString()
            val nombreModificar= view.findViewById<EditText>(R.id.ModificarNombreVela).text.toString()
            var precioModificar= view.findViewById<EditText>(R.id.ModificarPrecioVelas).text.toString()
            val descripcionModificar= view.findViewById<EditText>(R.id.ModificarDescripcionVelas).text.toString()
            val informacionModificar= view.findViewById<EditText>(R.id.ModificarInformacionVelas).text.toString()
            val imagenModificar= view.findViewById<EditText>(R.id.ModificarUrlVelas).text.toString()
            //val tamanoModificar= view.findViewById<EditText>(R.id.ModificarTamanoVelas).text.toString()
            //val tamanoModificar= view.findViewById<EditText>(R.id.ModificarTamanoVelas).text.toString()

            if(precioModificar=="") precioModificar="-1.0"

           db.modificarVela(codigoModificar,nombreModificar,precioModificar.toDouble(),descripcionModificar,informacionModificar,imagenModificar,"","")
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