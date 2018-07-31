package diamond

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.lang.System.lineSeparator

class `Diamond Kata Test` {

    @Test fun `print diamond to A`() = assertDiamond('A') {
        """
          A
          """
    }

    @Test fun `print diamond to C`() = assertDiamond('C') {
        """
            A
           B B
          C   C
           B B
            A
            """
    }

    @Test fun `print diamond to E`() = assertDiamond('E') {
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
    val endIndex = aToZIndexed.first { it.value == endChar }.index
    val topDiamond = aToZIndexed
        .takeWhile { (_, char) -> char <= endChar }
        .map { padChars(it.index, it.value, endIndex) }
        .toList()

    return (topDiamond + topDiamond.asReversed().tail()).joinToString(lineSeparator())
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

fun <T> List<T>.tail(): List<T> = if (this.size > 2) subList(1, lastIndex + 1) else emptyList()

fun Int.spaces(): String = if (this == 0) "" else String.format("%" + this + "s", "")

