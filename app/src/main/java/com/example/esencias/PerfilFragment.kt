package com.example.esencias

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class PerfilFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_perfil, container, false)
        val fotoPerfil: ImageView = view.findViewById(R.id.fotoPerfil)
        val flechaVolver: ImageView = view.findViewById(R.id.FlechaVolverPerfil)
        val nombreUsuario: TextView = view.findViewById(R.id.nombreUsuario)
        val correoUsuario: TextView = view.findViewById(R.id.correo)
        val telefonoUsuario: TextView = view.findViewById(R.id.telefono)
        val direccionUsuario: TextView = view.findViewById(R.id.direccion)
        val contenedorMisPedidos: LinearLayout = view.findViewById(R.id.ContenedorMisPedidos)
        val contenedorCambiarPass: LinearLayout = view.findViewById(R.id.ContenedorCambiarPass)

        contenedorCambiarPass.setOnClickListener(){
            val intent = Intent(requireContext(), PasswordActivity::class.java)
            startActivity(intent)
        }
        contenedorMisPedidos.setOnClickListener(){
            cargarFragment(Pedidos())
        }

        flechaVolver.setOnClickListener(){
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        val fotoString= if(Usuario.fotoPerfil.isNullOrBlank()) "https://i.pinimg.com/1200x/c2/8d/ee/c28deec925acb5beb6b2e8b5ed2a2dd3.jpg" else Usuario.fotoPerfil

        Glide.with(requireContext())
            .load(fotoString)
            .transform(CenterCrop(), RoundedCorners(200))
            .into(fotoPerfil)

        nombreUsuario.setText(if(Usuario.nombre.isNullOrBlank()) "Desconocido" else Usuario.nombre)
        correoUsuario.setText(if(Usuario.correo.isNullOrBlank()) "Desconocido" else Usuario.correo)
        telefonoUsuario.setText(if(Usuario.tlfn.isNullOrBlank()) "Desconocido" else Usuario.tlfn)
        direccionUsuario.setText(if(Usuario.direccion.isNullOrBlank()) "Desconocido" else Usuario.direccion)
        return view
    }

    private fun cargarFragment(fragment:Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_esencias, fragment)
            .addToBackStack(null)
            .commit()
    }
}