import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.layout.VBox
import javafx.stage.Stage
import java.io.File

class ComprasApp : Application() {
    override fun start(stage: Stage) {
        val carpeta = File("txt")
        if (!carpeta.exists()) carpeta.mkdir()

        val archivo = File("txt/arxiu.txt")
        val resumen = File("txt/resumen.txt")
        val textArea = TextArea()
        val input = TextField()
        input.promptText = "Formato: Nombre:Producto"

        val btnVer = Button("📄 Ver compras")
        val btnResumen = Button("📊 Ver resumen")
        val btnAgregar = Button("➕ Añadir compra")

        btnVer.setOnAction {
            textArea.text = if (archivo.exists()) archivo.readText() else "Archivo vacío."
        }

        btnResumen.setOnAction {
            val compras = mutableMapOf<String, MutableSet<String>>()
            archivo.forEachLine {
                val partes = it.split(":")
                if (partes.size == 2) {
                    val cliente = partes[0].trim()
                    val producto = partes[1].trim()
                    compras.getOrPut(cliente) { mutableSetOf() }.add(producto)
                }
            }

            resumen.writeText("")
            val resultado = compras.map { (cliente, productos) ->
                "$cliente ha comprado: ${productos.joinToString(", ")}"
            }.joinToString("\n")
            resumen.writeText(resultado)
            textArea.text = resultado
        }

        btnAgregar.setOnAction {
            val linea = input.text
            if (linea.contains(":")) {
                archivo.appendText("$linea\n")
                input.clear()
                textArea.text = "Compra añadida."
            } else {
                textArea.text = "Formato incorrecto. Usa Nombre:Producto"
            }
        }

        val layout = VBox(10.0, input, btnAgregar, btnVer, btnResumen, textArea)
        val scene = Scene(layout, 400.0, 400.0)
        stage.scene = scene
        stage.title = "Gestor de Compras - JavaFX"
        stage.show()
    }
}

fun main() {
    Application.launch(ComprasApp::class.java)
}
