class Robot(nombre: String) {
    var nombre: String = ""
    var posX: Int = 0
    var posY: Int = 0
    var direccion: String = ""

    init {
        require(nombre.isNotBlank()) { "El nombre no puede estar vacío" }
        this.nombre = nombre
        reiniciarPosicion()
    }

    private fun reiniciarPosicion() {
        posX = 0
        posY = 0
        direccion = "PositiveY"
    }

    fun mover(pasos: IntArray) {
        for (paso in pasos) {
            when (direccion) {
                "PositiveY" -> posY += paso
                "NegativeY" -> posY -= paso
                "PositiveX" -> posX += paso
                "NegativeX" -> posX -= paso
            }
            cambiarDireccion()
        }
    }

    private fun cambiarDireccion() {
        direccion = when (direccion) {
            "PositiveY" -> "PositiveX"
            "PositiveX" -> "NegativeY"
            "NegativeY" -> "NegativeX"
            "NegativeX" -> "PositiveY"
            else -> throw IllegalStateException("Dirección inválida")
        }
    }

    fun mostrarPosicion() {
        println("$nombre está en ($posX, $posY) $direccion.")
    }
}

fun main() {
    print("Ingresa el nombre del robot: ")
    val nombre = readln()

    try {
        val robot1 = Robot(nombre)

        print("Ingresa los pasos separados por comas sin espacios: ")
        val input = readln()

        if (input?.isNotBlank() == true) {
            val pasos = input.split(",").map { it.toIntOrNull()?: 0 }.toIntArray()
            robot1.mover(pasos)
            robot1.mostrarPosicion()
        } else {
            println("Entrada no válida")
        }
    } catch (e: IllegalArgumentException) {
        println(e.message)
    }
}
