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

//Más trabajo con ficheros, entrada y lectura de datos (nombres y notas de alumnos)

fun main() {

    //bloque guarda datos en archivo
 fun guardarEnArchivo(){
    val resumen = File("txt/resumen.txt")
    var notasAlumnos = mutableMapOf<String,Int>()
    println("Hola, bienvenido al gestor de notas mas guay del mundo...")
    println("Dime las notas de 5 alumnos separado por ':' (Ej: Ana:5)")
    while (notasAlumnos.size<5){
        var alumno = readln()
        var nombre = alumno.split(":")[0]
        var nota = alumno.split(":")[1].toInt()
        notasAlumnos.put(nombre,nota)
    }
    //El orden de las variables importa fijate!!!
    for ((alumno,nota)in notasAlumnos){
        resumen.appendText("$alumno:$nota\n")
    }
 }
    guardarEnArchivo()

    //bloque leer archivo sacar media, y mayor nota
    fun leerDeArchivo(){
        val datos =File("txt/resumen.txt")
        var datosAlumno= mutableMapOf<String,Int>()
        var notaMayor = 0
        var nombreNota = ""
        var media = 0

        datos.forEachLine { line ->
            val partes = line.split(":")
            val nombre = partes[0]
            val nota = partes[1].toInt()
            datosAlumno[nombre] = nota
        }

        for ((alumno,nota) in datosAlumno){
            media += nota
            if (nota>notaMayor){
                notaMayor = nota
                nombreNota = alumno
            }
        }
        media = media/datosAlumno.size
        println("El alumno con mayor nota es: $nombreNota ;)")
        println("")
        println("la nota media es de: $media")
    }

    leerDeArchivo()
}
