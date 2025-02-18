package com.example.esencias

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton

class Pedidos : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_pedidos, container, false)

        view.findViewById<ImageButton>(R.id.FlechaPedido).setOnClickListener {
            // Regresar al fragmento anterior en la pila de retroceso
            parentFragmentManager.popBackStack()
        }

        return view
    }

    companion object {


        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Pedidos().apply {
                arguments = Bundle().apply {

                }
            }
    }
}