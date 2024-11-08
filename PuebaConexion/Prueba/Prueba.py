import mysql.connector

conexion = mysql.connector.connect(
    host="localhost",
    user="root",
    passwd="",
    database="prueba",
    port="3306",
)



try:
    if conexion.is_connected():
        print("\n Conectado\n")

    cursor = conexion.cursor()

    cursor.execute("SELECT * FROM Productos")

    productos = cursor.fetchall()

    if len(productos) > 0:
        print(productos[0])  # Mostrar el primer producto (posición 0)
    else:
        print("No se encontraron productos.")



except:
    print("No se pudo conectar")

finally:
    if 'conexion' in locals() and conexion.is_connected():
        cursor.close()
        conexion.close()
        print("\n Conexión cerrada")