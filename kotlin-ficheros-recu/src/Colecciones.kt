fun main() {
    val lista = mutableListOf("Juan", "Pedro", "Marti", "Ana", "Lola")
    println("Cantidad de alumnos: ${lista.size}")

    println("Alumnos ordenados:")
    for (nombre in lista.sorted()) println(nombre)

    println("Alumnos con más de 4 letras:")
    for (nombre in lista) if (nombre.length > 4) println(nombre)

    val setNumeros = mutableSetOf<Int>()
    while (setNumeros.size < 5) {
        println("Introduce un número:")
        val num = readln().toIntOrNull()
        if (num != null) setNumeros.add(num)
    }
    println("Números únicos introducidos: $setNumeros")

    val notas = mutableMapOf("Ana" to 6, "Luis" to 4, "Pedro" to 8)
    val media = notas.values.average()
    val mejor = notas.maxByOrNull { it.value }
    println("Media: $media")
    println("Mejor nota: ${mejor?.key} con ${mejor?.value}")
}
