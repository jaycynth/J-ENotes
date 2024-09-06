package com.techne.jenotes.presentation.notes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp



class RichTextState {
    var currentFontWeight by mutableStateOf<FontWeight?>(null)
    var currentFontStyle by mutableStateOf<FontStyle?>(null)
    var currentTextDecoration by mutableStateOf<TextDecoration?>(null)
    var currentFontSize by mutableStateOf(16.sp)
    var currentColor by mutableStateOf(Color.Black)
    var currentTextAlign by mutableStateOf<TextAlign?>(null)
    private var content by mutableStateOf("")

    fun toggleSpanStyle(fontWeight: FontWeight? = null, fontStyle: FontStyle? = null, fontSize: TextUnit = 16.sp, color: Color = Color.Black) {
        currentFontWeight = fontWeight
        currentFontStyle = fontStyle
        currentFontSize = fontSize
        currentColor = color
    }

    fun toggleSpanStyle(textDecoration: TextDecoration?) {
        currentTextDecoration = textDecoration
    }

    fun toggleParagraphStyle(textAlign: TextAlign?) {
        currentTextAlign = textAlign
    }

    fun updateText(text: String) {
        content = text
    }

    fun toHtml(): String {
        return content
    }
}
