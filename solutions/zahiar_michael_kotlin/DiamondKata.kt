fun createDiamond(size: Int) {
    val lines = (size*2)-1

    (1.rangeTo(lines/2)).forEach{
        printDiamondLine(it, size)
    }

    (((lines/2)-1).downTo(1)).forEach{
        printDiamondLine(it, size)
    }
}

fun printDiamondLine(lineNum: Int, size: Int) {
    if (lineNum == 1 || lineNum == (size*2)-1) { // Top/Bottom Line
        val padding = size - 1

        printPadding(padding)
        print("*")
        printPadding(padding)
        println()
    } else if (lineNum == size) { // Middle Line
        val padding = (size * 2) - 2

        print("*")
        printPadding(padding)
        print("*")
        println()
    } else { // In-between Lines
        val externalPadding = (size - lineNum)
        val internalPadding = ((lineNum * 2) - 2)

        printPadding(externalPadding)
        print("*")
        printPadding(internalPadding)
        print("*")
        printPadding(externalPadding)
        println()
    }
}

fun printPadding(padding: Int) {
    (1.rangeTo(padding)).forEach {
        print(" ")
    }
}

fun main(args : Array<String>) {
    createDiamond(13)
}
