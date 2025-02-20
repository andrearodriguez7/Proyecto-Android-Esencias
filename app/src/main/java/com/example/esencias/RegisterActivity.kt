package com.example.esencias

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.register)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val volver: ImageButton = findViewById(R.id.volverButton)
        val registro: Button = findViewById(R.id.registerButton)

        volver.setOnClickListener {
            val intent= Intent(
                this@RegisterActivity,
                LoginActivity::class.java
            )
            startActivity(intent)
        }

        registro.setOnClickListener {
            val nombre = findViewById<EditText>(R.id.registerNombre).text.toString()
            val email = findViewById<EditText>(R.id.registerEmail).text.toString()
            val pass = findViewById<EditText>(R.id.registerContrasenia).text.toString()
            val repPass = findViewById<EditText>(R.id.registerRepetirContrasenia).text.toString()
            val direccion = findViewById<EditText>(R.id.registerDireccion).text.toString()
            val foto = findViewById<EditText>(R.id.TuFoto).text.toString()
            val telefono = findViewById<EditText>(R.id.registerTelefono).text.toString()

            if(pass != repPass){
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val bbdd=BBDD(this);

            when (bbdd.insertarUsuario(email, nombre, pass, direccion, foto, "usuario", telefono)) {
                0 ->Toast.makeText(this, "Usuario registrado con éxito.", Toast.LENGTH_SHORT).show()
                1 ->Toast.makeText(this, "Este correo ya está registrado.", Toast.LENGTH_SHORT).show()
                2 -> Toast.makeText(this, "Este usuario ya está registrado.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}