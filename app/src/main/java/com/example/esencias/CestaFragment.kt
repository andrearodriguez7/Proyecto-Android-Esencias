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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CestaFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdaptadorCesta
    private lateinit var listaProductos: MutableList<ProductosCompras>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cesta, container, false)

        val totalText: TextView =view.findViewById(R.id.Total)
        val pagarButton: Button =view.findViewById(R.id.pagarButton)
        val flechaCesta: ImageView= view.findViewById(R.id.FlechaCesta)
        val db = BBDD(requireContext())

        flechaCesta.setOnClickListener(){
            requireActivity().finish()
        }

        pagarButton.setOnClickListener(){

            val facturaFragment = FacturaFragment.newInstance(generarFacturaString())

            db.agregarPedido(Usuario.correo,listaProductos,listaProductos.sumOf{it.precio * it.cantidad})

            db.eliminarProductosCesta(Usuario.correo)

            listaProductos.clear()

            adapter.notifyDataSetChanged()

            actualizarTotal(totalText)

            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.main, facturaFragment)
            transaction.addToBackStack(null)
            transaction.commit()

        }

        recyclerView = view.findViewById(R.id.recyclerCesta)


        listaProductos = db.obtenerProductosCesta(Usuario.correo)

        actualizarTotal(totalText)

        adapter = AdaptadorCesta(requireContext(), listaProductos, db){
            actualizarTotal(totalText)
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        return view
    }

    fun generarFacturaString(): String {
        val totalFactura = listaProductos.sumOf { it.precio * it.cantidad }
        val facturaBuilder = StringBuilder()

        listaProductos.forEach { producto ->
            facturaBuilder.append("${producto.nombre} - ${producto.cantidad} x ${producto.precio} = ${producto.cantidad * producto.precio}\n\n")
        }

        facturaBuilder.append("\nTotal: $totalFactura")

        return facturaBuilder.toString()
    }

    private fun actualizarTotal(totalText:TextView){
        val totalCompra = listaProductos.sumOf{it.precio * it.cantidad}
        totalText.text = String.format("%.2f€", totalCompra)
    }
}
