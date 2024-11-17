package com.example.esencias

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.login_activity)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val registro: TextView = findViewById(R.id.registro)

        val inicio: Button = findViewById(R.id.entrarButton)

        registro.setOnClickListener {
            val intent = Intent(
                this@LoginActivity,
                RegisterActivity::class.java
            )
            startActivity(intent)
        }

        inicio.setOnClickListener{
            var nombre:String?=findViewById<EditText>(R.id.loginEmail).text.toString()
            val pass=findViewById<EditText>(R.id.loginPass).text.toString()
            val bd=BBDD(this)
            nombre=bd.encontrarUsuario(nombre)
            if(nombre!=null){
                if(bd.verificarUsuario(nombre,pass)){
                    val intent = Intent(
                        this@LoginActivity,
                        AppActivity::class.java
                    )
                    startActivity(intent)
                }else{
                    Toast.makeText(this,"Contraseña incorrecta...",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this,"Usuario no encontrado",Toast.LENGTH_LONG).show()
            }
        }

    }
}