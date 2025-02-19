package com.example.esencias

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Pedidos : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdaptadorPedidos
    private lateinit var listaPedidos: MutableList<Pedido>
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

        view.findViewById<ImageView>(R.id.FlechaPedido).setOnClickListener {
            // Regresar al fragmento anterior en la pila de retroceso
            parentFragmentManager.popBackStack()
        }
        recyclerView = view.findViewById(R.id.recyclerPedidos)
        val db = BBDD(requireContext())
        listaPedidos = db.obtenerPedidos(Usuario.correo)

        adapter = AdaptadorPedidos(requireContext(), listaPedidos)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
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