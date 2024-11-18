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

        val tablaCesta ="""
        CREATE TABLE Cesta (
            idCesta INTEGER PRIMARY KEY AUTOINCREMENT,
            correo TEXT NOT NULL UNIQUE,
            FOREIGN KEY (correo) REFERENCES Usuario(correo)
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
                correo TEXT NOT NULL,
                estado TEXT NOT NULL,
                fecha TEXT NOT NULL,
                importe REAL NOT NULL,
                FOREIGN KEY (correo) REFERENCES Usuario(correo)
            );
        """

        val tablaPedidosProductos = """
        CREATE TABLE Pedido_Producto (
            idPedido INTEGER NOT NULL,
            idProducto INTEGER NOT NULL,
            PRIMARY KEY (idPedido, idProducto),
            FOREIGN KEY (idPedido) REFERENCES Pedido(idPedido),
            FOREIGN KEY (idProducto) REFERENCES Producto(idProducto)
        );
        """
        val tablaCursos = """
        CREATE TABLE Curso (
            idProducto INTEGER PRIMARY KEY,
            plazasDisponibles INTEGER NOT NULL,
            plazasMaximas INTEGER NOT NULL,
            FOREIGN KEY (idProducto) REFERENCES Producto(idProducto)
        );
        """

        val tablaVelas = """
        CREATE TABLE Vela (
            idProducto INTEGER PRIMARY KEY,
            tamano TEXT NOT NULL,
            aromas TEXT NOT NULL,
            FOREIGN KEY (idProducto) REFERENCES Producto(idProducto)
        );
        """

        val insertVelas = """
            INSERT INTO Producto (precio, descripcion, imagen)
            VALUES
            (9.99, 'Vela aromática con esencia de lavanda', 'https://i.pinimg.com/originals/66/82/d3/6682d3b1da1a78b3843b951613be0288.jpg'),
            (12.99, 'Vela perfumada de vainilla', 'https://i.pinimg.com/originals/66/82/d3/6682d3b1da1a78b3843b951613be0288.jpg'),
            (15.99, 'Vela con fragancia de rosa', 'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fmejorconsalud.as.com%2Fwp-content%2Fuploads%2F2019%2F05%2Fhacer-velas-arom%25C3%25A1ticas-de-gel.jpg&f=1&nofb=1&ipt=9520c5f7bc035d20b99fd4c49ec9007622ff70f2c18c619b4e3b4f762646794c&ipo=images'),
            (10.99, 'Vela de coco con aroma tropical', 'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fmejorconsalud.as.com%2Fwp-content%2Fuploads%2F2019%2F05%2Fhacer-velas-arom%25C3%25A1ticas-de-gel.jpg&f=1&nofb=1&ipt=9520c5f7bc035d20b99fd4c49ec9007622ff70f2c18c619b4e3b4f762646794c&ipo=images'),
            (14.99, 'Vela de canela y especias', 'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fmejorconsalud.as.com%2Fwp-content%2Fuploads%2F2019%2F05%2Fhacer-velas-arom%25C3%25A1ticas-de-gel.jpg&f=1&nofb=1&ipt=9520c5f7bc035d20b99fd4c49ec9007622ff70f2c18c619b4e3b4f762646794c&ipo=images'),
            (15.99, 'Vela con fragancia de rosa', 'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fmejorconsalud.as.com%2Fwp-content%2Fuploads%2F2019%2F05%2Fhacer-velas-arom%25C3%25A1ticas-de-gel.jpg&f=1&nofb=1&ipt=9520c5f7bc035d20b99fd4c49ec9007622ff70f2c18c619b4e3b4f762646794c&ipo=images');

        """

        val insertVelas2 = """
            
            INSERT INTO Vela (idProducto, tamano, aromas)
            VALUES
            (1, 'Pequeña', 'Lavanda'),
            (2, 'Mediana', 'Vainilla'),
            (3, 'Grande', 'Rosa'),
            (4, 'Pequeña', 'Coco'),
            (5, 'Mediana', 'Canela'),
            (6, 'Pequeña', 'Coco');

        """

        db.execSQL(tablaUsuario)
        db.execSQL(tablaTarjeta)
        db.execSQL(tablaUsuario_Tarjeta)
        db.execSQL(tablaCesta)
        db.execSQL(tablaProducto)
        db.execSQL(tablaCestaProducto)
        db.execSQL(tablaPedido)
        db.execSQL(tablaPedidosProductos)
        db.execSQL(tablaCursos)
        db.execSQL(tablaVelas)
        db.execSQL(insertVelas)
        db.execSQL(insertVelas2)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Usuario_Tarjeta")
        db.execSQL("DROP TABLE IF EXISTS Cesta_Producto")
        db.execSQL("DROP TABLE IF EXISTS Pedido_Producto")
        db.execSQL("DROP TABLE IF EXISTS Tarjeta")
        db.execSQL("DROP TABLE IF EXISTS Cesta")
        db.execSQL("DROP TABLE IF EXISTS Producto")
        db.execSQL("DROP TABLE IF EXISTS Pedido")
        db.execSQL("DROP TABLE IF EXISTS Curso")
        db.execSQL("DROP TABLE IF EXISTS Vela")
        db.execSQL("DROP TABLE IF EXISTS Usuario")
        onCreate(db)
    }

    override fun onOpen(db: SQLiteDatabase) {
        super.onOpen(db)
        db.execSQL("PRAGMA foreign_keys=ON;")
    }
    /**
     * Intenta la insercion de un usuario,
     * Devuelve un Int:
     *  0: Insercion correcta
     *  1: Correo duplicado en bbdd
     *  2: Nombre duplicado en bbdd
     * */
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
    /**
     * Busca un usuario por su nombre o correo.
     * Devuelve el nombre encontrado en BBDD, si no se encuentra, devolverá null.
     * */
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
    /**
     * Dado un nombre y una contraseña, comprueba en BBDD
     * Devuelve:
     *  True: la contraseña es correcta
     *  False: la contraseña es incorrecta
     * */
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

    fun listaVelas(): List<Vela> {
        val listaVelas = mutableListOf<Vela>()
        val db = this.readableDatabase

        val query = """
            SELECT 
                Producto.descripcion AS titulo,
                Producto.precio AS precio,
                Producto.imagen AS imagen,
                Producto.descripcion AS descripcion,
                Vela.tamano AS tamano,
                Vela.aromas AS aromas
            FROM Producto
            INNER JOIN Vela ON Producto.idProducto = Vela.idProducto;
        """

        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo"))
                val precio = cursor.getDouble(cursor.getColumnIndexOrThrow("precio")).toString()
                val imagen = cursor.getString(cursor.getColumnIndexOrThrow("imagen"))
                val descripcion = cursor.getString(cursor.getColumnIndexOrThrow("descripcion"))
                val tamano = cursor.getString(cursor.getColumnIndexOrThrow("tamano"))
                val aromas = cursor.getString(cursor.getColumnIndexOrThrow("aromas"))

                listaVelas.add(
                    Vela(
                        titulo = titulo,
                        precio = precio,
                        imagen = imagen,
                        descripcion = descripcion,
                        tamano = tamano,
                        aromas = aromas
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return listaVelas
    }
}