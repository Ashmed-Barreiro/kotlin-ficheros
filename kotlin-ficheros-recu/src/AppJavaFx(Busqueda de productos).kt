package org.example.examenr

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.layout.VBox
import javafx.stage.Stage
import jdk.incubator.foreign.CLinker.VaList
import java.io.File

class HelloApplication : Application() {
    override fun start(stage: Stage){
        val inputClient = TextField()
        inputClient.promptText="Dime el cliente que quieres buscar??"
        val db = File("txt/arxiu.txt")
        val buscar = Button("Buscar")
        val resultado= TextArea()

        buscar.setOnAction {
            val nombreBuscado = inputClient.text.trim()
            val comprasCliente = mutableListOf<String>()

            db.forEachLine { line ->
                val partes = line.split(":")
                if (partes.size == 2) {
                    val nombre = partes[0].trim()
                    val producto = partes[1].trim()

                    // Solo añadimos si es justo el cliente buscado
                    if (nombre == nombreBuscado) {
                        comprasCliente.add(producto)
                    }
                }
            }

            // Resultado final SOLO si se encontró el cliente
            resultado.text = if (comprasCliente.isEmpty()) {
                "Cliente no encontrado."
            } else {
                "Las compras de $nombreBuscado son: ${comprasCliente.joinToString(", ")}"
            }
        }

        val layout= VBox(10.0,inputClient,buscar,resultado)
        stage.scene= Scene(layout,400.0,300.0)
        stage.title = "Filtro por Cliente"
        stage.show()
    }
}

fun main() {
    Application.launch(HelloApplication::class.java)
}
