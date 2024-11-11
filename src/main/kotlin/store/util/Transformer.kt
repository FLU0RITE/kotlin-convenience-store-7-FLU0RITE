package store.util

class Transformer {
    fun formatWithPadding(text: String, width: Int): String {
        val actualWidth = text.sumOf { (if (it.code > 0xFF) 2 else 1).toInt() }
        val padding = width - actualWidth
        return text + ConstantText.ONE_SPACE.text.repeat(padding.coerceAtLeast(0))
    }
}