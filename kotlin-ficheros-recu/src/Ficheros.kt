import java.io.File

fun main() {
    val archivo = File("txt/arxiu.txt")
    archivo.writeText("")
    val nombres = listOf("Ana:Pan", "Luis:Leche", "Ana:Queso", "Pedro:Pan", "Luis:Pan", "Ana:Leche")
    for (linea in nombres) archivo.appendText("$linea\n")

    val compras = mutableMapOf<String, MutableSet<String>>()
    archivo.forEachLine {
        val partes = it.split(":")
        if (partes.size == 2) {
            val cliente = partes[0]
            val producto = partes[1]
            compras.getOrPut(cliente) { mutableSetOf() }.add(producto)
        }
    }

    val resumen = File("txt/resumen.txt")
    resumen.writeText("")
    for ((cliente, productos) in compras) {
        resumen.appendText("$cliente ha comprado: ${productos.joinToString(", ")}\n")
    }
    println("Resumen generado en txt/resumen.txt")
}
