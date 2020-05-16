import tkinter as tk
import tkinter.messagebox as messagebox
from datetime import datetime
import View
import UtilDB


class Controller:

    def __init__(self):
        self.root = tk.Tk()
        self.view = View.View(self.root, self)
        self.utilDB = UtilDB.Connection()
        self.utilDB.getConnection()  # Abre la conexión a la BBDD
        self.btnPulsado = "" # Para saber si se ha pulsado un boton de campo dos veces, y ordenar de forma descendente
        self.ascendente = False # Ordena en orden ascendente o descendente
        self.root.mainloop()

    def actualizarPantallaProductos(self, ordenarPorCampo):
        self.limpiarPantallaProductos()
        self.rellenarPantallaProductos(ordenarPorCampo)

    def actualizarGrafico(self):
        self.view.frameGrafico.destroy()
        self.view.pintarGrafico()

    def limpiarPantallaProductos(self):
        elements = self.view.frameDatos.grid_slaves()
        for i in range(len(elements) - len(self.view.campos)):
            elements[i].destroy()

    def rellenarPantallaProductos(self, ordenarPorCampo):
        registros = self.utilDB.getAllProductos(ordenarPorCampo)
        for i in range(len(registros)):
            for j in range(len(registros[i])):
                registro = tk.Label(self.view.frameDatos, text=registros[i][j])
                registro.grid(row=i + 1, column=j)

    def insertar(self):
        nombre = self.view.txtNombre.get()
        precio = self.view.txtPrecio.get()
        supermercado = self.view.txtSupermercado.get()
        fecha = self.view.spnAnho.get() + "-" + self.view.spnMes.get() + "-" + self.view.spnDia.get()
        msgError = self.comprobarDatos(nombre, supermercado, precio, fecha)
        if msgError == "":
            self.utilDB.insertarProducto(nombre, precio, supermercado, fecha)
            self.actualizarPantallaProductos("")
            self.actualizarGrafico()
            messagebox.showinfo("Informacion", "Se ha insertado el producto correctamente")
        else:
            messagebox.showerror("Error", msgError)

    def comprobarDatos(self, nombre, supermercado, precio, fecha):
        msgError = ""
        if nombre.strip() == "":
            msgError += "El campo NOMBRE no puede quedar vacío\n\n"
        if supermercado.strip() == "":
            msgError += "El campo SUPERMERCADO no puede quedar vacío\n\n"
        try:
            precio = float(precio)
        except:
            if precio.find(",") == 1:
                msgError += "Precio incorrecto. El separador de los decimales debe ser un punto (.)\n\n"
            else:
                msgError += "En el campo PRECIO debe introducir un valor numérico\n\n"
        try:
            datetime.strptime(fecha, '%Y-%m-%d')
        except:
            msgError += "Fecha incorrecta"

        return msgError

    def eliminar(self):
        id = self.view.txtId.get()
        producto = self.utilDB.getProducto(id)
        if producto == None:
            messagebox.showerror("Error", "No se ha encontrado ningun producto\nRevise el ID")
        else:
            seleccion = messagebox.askyesno("war", "¿Esta seguro de que desea eliminar el siguiente producto?\n\n"
                                            + producto[1] + "\n"
                                            + str(producto[2]) + "\n"
                                            + producto[3] + "\n"
                                            + str(producto[4]))
            if seleccion:
                self.utilDB.eliminarProducto(id)
                self.actualizarPantallaProductos("")
                self.actualizarGrafico()
                messagebox.showinfo("Informacion", "Producto eliminado satisfactoriamente")

    def ordenarListaProductos(self, ordenarPorCampo):
        if self.btnPulsado == "":
            self.ascendente = True
        else:
            if self.btnPulsado != ordenarPorCampo:
                self.ascendente = True
            else:
                if self.ascendente:
                    self.ascendente = False
                else:
                    self.ascendente = True
        self.btnPulsado = ordenarPorCampo
        if self.ascendente:
            self.actualizarPantallaProductos(ordenarPorCampo)
        else:
            self.actualizarPantallaProductos(ordenarPorCampo + " desc")




controller = Controller()