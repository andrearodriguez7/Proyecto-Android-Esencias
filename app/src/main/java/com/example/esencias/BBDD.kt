package com.example.esencias
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BBDD(context: Context) : SQLiteOpenHelper(context, "esenciasBBDD.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {

        val tablaUsuario = """
            CREATE TABLE Usuario (
                correo TEXT PRIMARY KEY,
                nombre TEXT NOT NULL UNIQUE,
                pass TEXT NOT NULL,
                direccion TEXT,
                fotoPerfil TEXT,
                privilegios TEXT,
                tlfn TEXT
            );
        """

        val tablaTarjeta = """
            CREATE TABLE Tarjeta (
                nCuenta TEXT PRIMARY KEY,
                caducidad TEXT,
                codSeguridad TEXT,
                tipo TEXT
            );
        """

        val tablaUsuario_Tarjeta = """
            CREATE TABLE Usuario_Tarjeta (
                correo TEXT,
                nCuenta TEXT,
                PRIMARY KEY (correo, nCuenta),
                FOREIGN KEY (correo) REFERENCES Usuario(correo),
                FOREIGN KEY (nCuenta) REFERENCES Tarjeta(nCuenta)
            );
        """

        val tablaCesta = """
            CREATE TABLE Cesta (
                idCesta INTEGER PRIMARY KEY AUTOINCREMENT
            );
        """

        val tablaProducto = """
            CREATE TABLE Producto (
                idProducto INTEGER PRIMARY KEY AUTOINCREMENT,
                precio REAL,
                descripcion TEXT,
                imagen TEXT
            );
        """

        val tablaCestaProducto = """
            CREATE TABLE Cesta_Producto (
                idCesta INTEGER,
                idProducto INTEGER,
                PRIMARY KEY (idCesta, idProducto),
                FOREIGN KEY (idCesta) REFERENCES Cesta(idCesta),
                FOREIGN KEY (idProducto) REFERENCES Producto(idProducto)
            );
        """

        val tablaPedido = """
            CREATE TABLE Pedido (
                idPedido INTEGER PRIMARY KEY AUTOINCREMENT,
                correo TEXT,
                estado TEXT,
                fecha TEXT,
                importe REAL,
                FOREIGN KEY (correo) REFERENCES Usuario(correo)
            );
        """

        db.execSQL(tablaUsuario)
        db.execSQL(tablaTarjeta)
        db.execSQL(tablaUsuario_Tarjeta)
        db.execSQL(tablaCesta)
        db.execSQL(tablaProducto)
        db.execSQL(tablaCestaProducto)
        db.execSQL(tablaPedido)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Usuario")
        db.execSQL("DROP TABLE IF EXISTS Tarjeta")
        db.execSQL("DROP TABLE IF EXISTS Usuario_Tarjeta")
        db.execSQL("DROP TABLE IF EXISTS Cesta")
        db.execSQL("DROP TABLE IF EXISTS Producto")
        db.execSQL("DROP TABLE IF EXISTS Cesta_Producto")
        db.execSQL("DROP TABLE IF EXISTS Pedido")
        onCreate(db)
    }

    override fun onOpen(db: SQLiteDatabase) {
        super.onOpen(db)
        db.execSQL("PRAGMA foreign_keys=ON;")
    }

    fun insertarUsuario(correo:String,nombre:String, pass:String, direccion:String?,fotoPerfil:String?, privilegios:String?, tlfn:String?):Int{

        val db=this.writableDatabase
        val values= ContentValues().apply {
            put("correo",correo)
            put("nombre",nombre)
            put("pass",pass)
            put("direccion",direccion)
            put("fotoPerfil",fotoPerfil)
            put("privilegios",privilegios)
            put("tlfn",tlfn)
        }
        try{
            db.insertOrThrow("usuario",null,values)
        }catch (e:SQLiteConstraintException){

            if(e.message?.contains("UNIQUE constraint failed: Usuario.correo")==true) return 1
            if(e.message?.contains("UNIQUE constraint failed: Usuario.nombre")==true) return 2

        }finally{
            db.close()
        }
        return 0
    }

    fun encontrarUsuario(nombre:String?):String?{
        val db = this.readableDatabase
        val query = "SELECT nombre FROM Usuario WHERE nombre = ? OR correo = ?"
        val cursor = db.rawQuery(query, arrayOf(nombre,nombre))
        var ret:String?=null

        if(cursor.moveToFirst()) ret=cursor.getString(0)

        cursor.close()
        db.close()

        return ret
    }

    fun verificarUsuario(nombre: String, pass: String):Boolean{

        val db=this.readableDatabase
        val query="SELECT nombre, pass FROM Usuario WHERE nombre = ?"
        val cursor = db.rawQuery(query, arrayOf(nombre))
        var ret=false
        if(cursor.moveToFirst()) if(cursor.getString(1) == pass) ret=true
        db.close()
        cursor.close()
        return ret
    }
}