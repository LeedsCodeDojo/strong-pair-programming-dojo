import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.lang.System.lineSeparator

@DisplayName("Diamond Kata Tests")
class DiamondTest {

    @Test
    fun canPrintA() = assertDiamond('A') {
        """
          A
          """
    }

    @Test
    fun canPrintC() = assertDiamond('C') {
        """
            A
           B B
          C   C
           B B
            A"""
    }

    @Test
    fun canPrintE() = assertDiamond('E') {
        """
               A
              B B
             C   C
            D     D
           E       E
            D     D
             C   C
              B B
               A
           """
    }

    private fun assertDiamond(char: Char, f: () -> String) {
        diamondStr(char).also {
            println("Printing Diamond for: $char ${System.lineSeparator()}$it")
            assertEquals(f().trimIndent(), it)
        }
    }
}

val aToZ: Sequence<Char> =
    generateSequence('A') { if (it == 'Z') null else it.inc() }

val aToZIndexed = aToZ.withIndex()

fun diamondStr(endChar: Char): String {
    val topDiamond = aToZIndexed
        .takeWhile { (_, char) -> char <= endChar }
        .map { padChars(it.index, it.value, aToZIndexed.first { it.value == endChar }.index) }
        .toList()

    return (topDiamond + topDiamond.tail().asReversed()).joinToString(lineSeparator())
}

fun padChars(index: Int, char: Char, endCharIndex: Int): String {
    val startPad = getStartPad(index, endCharIndex)
    val insidePad = getInnerPad(index)
    return when (index) {
        0 -> "$startPad$char"
        endCharIndex -> "$char$insidePad$char"
        else -> "$startPad$char$insidePad$char"
    }
}

fun getInnerPad(index: Int) = (index * 2 - 1).spaces()

fun getStartPad(currentCharIndex: Int, endCharIndex: Int): String =
    if (currentCharIndex == endCharIndex && endCharIndex > 0)
        (currentCharIndex + 1).spaces()
    else
        (endCharIndex - currentCharIndex).spaces()

fun <T> List<T>.tail(): List<T> = subList(0, lastIndex)

fun Int.spaces(): String = if (this == 0) "" else String.format("%" + this + "s", "")

