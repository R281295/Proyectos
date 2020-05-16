import tkinter as tk
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg
import pylab as pl
import numpy as np

import UtilDB


class View:
    def __init__(self, view, controller):
        self.controller = controller

        self.view = view
        self.view.title("Gastos")
        self.container = tk.Frame(self.view)
        self.canvas = tk.Canvas(self.container, width=600)
        self.scrollbar = tk.Scrollbar(self.container, orient="vertical", command=self.canvas.yview)
        self.frameDatos = tk.Frame(self.canvas)
        self.frameDatos.bind("<Configure>", lambda e: self.canvas.configure(scrollregion=self.canvas.bbox("all")))
        self.canvas.create_window((0, 0), window=self.frameDatos, anchor="nw")
        self.canvas.configure(yscrollcommand=self.scrollbar.set)

        self.utilDB = UtilDB.Connection()
        self.utilDB.getConnection()
        self.campos = self.utilDB.getCampos()
        self.registros = self.utilDB.getAllProductos("")

        self.frameInsertar = tk.Frame()
        self.frameEliminar = tk.Frame()

        btnID = tk.Button(self.frameDatos, text=self.campos[0], command=lambda: self.controller.ordenarListaProductos("id"))
        btnID.grid(row=0, column=0, padx=5)
        btnNombre = tk.Button(self.frameDatos, text=self.campos[1], command=lambda: self.controller.ordenarListaProductos("nombre"))
        btnNombre.grid(row=0, column=1, padx=5)
        btnPrecio = tk.Button(self.frameDatos, text=self.campos[2], command=lambda: self.controller.ordenarListaProductos("precio"))
        btnPrecio.grid(row=0, column=2, padx=5)
        btnSupermercado = tk.Button(self.frameDatos, text=self.campos[3], command=lambda: self.controller.ordenarListaProductos("supermercado"))
        btnSupermercado.grid(row=0, column=3, padx=5)
        btnFecha = tk.Button(self.frameDatos, text=self.campos[4], command=lambda: self.controller.ordenarListaProductos("fecha_compra"))
        btnFecha.grid(row=0, column=4, padx=5)
        # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #

        for i in range(len(self.registros)):
            for j in range(len(self.registros[i])):
                registro = tk.Label(self.frameDatos, text=self.registros[i][j])
                registro.grid(row=i + 1, column=j)

        self.container.grid(row=0, column=0, rowspan=2)
        self.canvas.grid(row=0, column=0)
        self.scrollbar.grid(row=0, column=1, sticky=tk.NS)

        self.lblInsertar = tk.Label(self.frameInsertar, text="────────────── Insertar ──────────────")
        self.lblInsertar.grid(row=0, column=0, columnspan=2 + 3, pady=(10, 5))
        self.lblNombre = tk.Label(self.frameInsertar, text="Nombre")
        self.lblNombre.grid(row=1, column=0)
        self.lblPrecio = tk.Label(self.frameInsertar, text="Precio")
        self.lblPrecio.grid(row=1, column=1, columnspan=3)
        self.txtNombre = tk.Entry(self.frameInsertar)
        self.txtNombre.grid(row=2, column=0, padx=5, pady=(0, 10))
        self.txtPrecio = tk.Entry(self.frameInsertar)
        self.txtPrecio.grid(row=2, column=1, padx=5, pady=(0, 10), columnspan=3)
        self.lblSupermercado = tk.Label(self.frameInsertar, text="Supermercado")
        self.lblSupermercado.grid(row=3, column=0)
        self.lblFecha = tk.Label(self.frameInsertar, text="Fecha compra (DD/MM/YYYY)")
        self.lblFecha.grid(row=3, column=1, columnspan=3)
        self.txtSupermercado = tk.Entry(self.frameInsertar)
        self.txtSupermercado.grid(row=4, column=0, padx=5)
        self.spnDia = tk.Spinbox(self.frameInsertar, from_=1, to=31, width=5, state="readonly")
        self.spnDia.grid(row=4, column=1)
        self.spnMes = tk.Spinbox(self.frameInsertar, from_=1, to=12, width=5, state="readonly")
        self.spnMes.grid(row=4, column=2)
        self.spnAnho = tk.Spinbox(self.frameInsertar, from_=2015, to=2021, width=5, state="readonly")
        self.spnAnho.grid(row=4, column=3)
        self.btnInsertar = tk.Button(self.frameInsertar, text="Insertar", command=self.controller.insertar)
        self.btnInsertar.grid(row=5, column=1, pady=10, columnspan=3)

        self.lblEliminar = tk.Label(self.frameEliminar, text="────────────── Eliminar ──────────────")
        self.lblEliminar.grid(row=7, column=0, columnspan=2 + 3, pady=(10, 5))
        self.lblId = tk.Label(self.frameEliminar, text="ID de producto:")
        self.lblId.grid(row=8, column=0)
        self.txtId = tk.Entry(self.frameEliminar)
        self.txtId.grid(row=8, column=1, columnspan=3)
        self.btnEliminar = tk.Button(self.frameEliminar, text="Eliminar", command=self.controller.eliminar)
        self.btnEliminar.grid(row=9, column=1, pady=10, columnspan=3)

        self.pintarGrafico()

        self.frameInsertar.grid(row=0, column=2)
        self.frameEliminar.grid(row=1, column=2)

    def pintarGrafico(self):
        self.frameGrafico = tk.Frame(height=20)

        self.fig = pl.figure(figsize=(5, 3))
        self.ax = pl.axes([0.025, 0.1, 0.95, 0.9], polar=False)

        self.supermercadoGastoTotal = self.utilDB.getTotalGastosSupermercado()
        self.numBarras = len(self.supermercadoGastoTotal)
        print(self.numBarras)
        if self.numBarras > 0:
            self.posBarras = np.arange(0.0, 2 * np.pi, 2 * np.pi / self.numBarras)
            self.theta = self.posBarras

            self.radii = []
            for i in range(len(self.supermercadoGastoTotal)):
                totalGasto = self.supermercadoGastoTotal[i][1]
                self.radii.append(float(totalGasto))
                pl.text(self.posBarras[i], totalGasto, totalGasto, ha='center', va='top')

            self.width = 0.5 # Ancho de las barras
            self.bars = pl.bar(self.theta, self.radii, width=self.width, bottom=0.0)

            for i in range(self.numBarras):
                self.bars[i].set_facecolor(self.getRandomColor())

            self.supermercados = []
            for i in range(len(self.supermercadoGastoTotal)):
                self.supermercados.append(self.supermercadoGastoTotal[i][0])
            self.ax.set_xticklabels(self.supermercados)
            self.ax.set_yticklabels([])
            self.ax.set_xticks(self.posBarras)
            self.testCanvas = FigureCanvasTkAgg(self.fig, master=self.frameGrafico)
            self.testCanvas.draw()
            self.testCanvas.get_tk_widget().pack(side=tk.TOP, fill=tk.BOTH, expand=1)
            self.frameGrafico.grid(row=2, column=0)


    def getRandomColor(self):
        randomColor = []
        for i in range(3):
            randomColor.append(np.random.rand())
        return randomColor