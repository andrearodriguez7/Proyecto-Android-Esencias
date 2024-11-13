package com.example.esencias
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BBDD(context: Context) : SQLiteOpenHelper(context, "esenciasBBDD.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val tablaUsuario = """
            CREATE TABLE Usuario (
                dni TEXT PRIMARY KEY,
                Nombre TEXT NOT NULL,
                Apellido1 TEXT NOT NULL,
                Apellido2 TEXT,
                Direccion TEXT,
                fotoPerfil TEXT,
                privilegios TEXT,
                correo TEXT,
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
                dni TEXT,
                nCuenta TEXT,
                PRIMARY KEY (dni, nCuenta),
                FOREIGN KEY (dni) REFERENCES Usuario(dni),
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
                dni TEXT,
                estado TEXT,
                fecha TEXT,
                importe REAL,
                FOREIGN KEY (dni) REFERENCES Usuario(dni)
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
}