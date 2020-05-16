import pymysql
import json

class Connection:
    def __init__(self):
        self.file = open("connection.json", "r")
        self.content = self.file.read()
        self.jsondecoded = json.loads(self.content)

        self.connection = None
        self.cursor = None


    def getConnection(self):
        # Patrón de diseño Singleton.
        if self.connection == None:
            self.connection = pymysql.connect(self.jsondecoded["Host"],
                                              self.jsondecoded["User"],
                                              self.jsondecoded["Password"],
                                              self.jsondecoded["BBDD"])
            self.cursor = self.connection.cursor()
        return self.connection

    def getCampos(self):
        self.cursor.execute("describe productos")
        data = self.cursor.fetchall()
        campos = []
        for i in range(len(data)):
            campos.append(data[i][0])
        return campos

    def getAllProductos(self, ordenarPorCampo):
        query = "select * from productos"
        if ordenarPorCampo != "":
            query = query + " order by " + ordenarPorCampo
        self.cursor.execute(query)
        data = self.cursor.fetchall()
        return data

    def getProducto(self, id):
        try:
            self.cursor.execute("select * from productos where id = " + id)
            data = self.cursor.fetchall()
            return data[0]
        except:
            return None

    def insertarProducto(self, nombre, precio, supermercado, fecha):
        self.cursor.execute("insert into productos (nombre, precio, supermercado, fecha_compra) values ('" +
                            nombre + "', " +
                            str(precio) + ", '" +
                            supermercado + "', '" +
                            fecha + "'" +
                            ")")
        self.connection.commit()

    def eliminarProducto(self, id):
        self.cursor.execute("delete from productos where id = " + id)
        self.connection.commit()

    def getTotalGastosSupermercado(self):
        self.connection.commit()
        self.cursor.execute("select supermercado, sum(precio) from productos group by supermercado;")
        data = self.cursor.fetchall()
        return data