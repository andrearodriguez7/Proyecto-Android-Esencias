package com.example.esencias

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


class SettingsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_settings, container, false)
        val fotoUsuario: ImageView = view.findViewById(R.id.fotoUsuario)
        val flecha:ImageView = view.findViewById(R.id.Flecha)
        val contenedor: LinearLayout = view.findViewById(R.id.ContenedorEditarPerfil)

        val fotoString= if(Usuario.fotoPerfil.isNullOrBlank()) "https://i.pinimg.com/1200x/c2/8d/ee/c28deec925acb5beb6b2e8b5ed2a2dd3.jpg" else Usuario.fotoPerfil
        Glide.with(requireContext())
            .load(fotoString)
            .transform(CenterCrop(), RoundedCorners(100))
            .into(fotoUsuario)

        flecha.setOnClickListener(){
            requireActivity().finish()
        }

        contenedor.setOnClickListener(){
            cargarFragment(PerfilFragment());
        }

        return view
    }

    private fun cargarFragment(fragment:Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_esencias, fragment)
            .addToBackStack(null)
            .commit()
    }
}