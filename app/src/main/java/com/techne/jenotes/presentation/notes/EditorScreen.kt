package com.techne.jenotes.presentation.notes


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.FormatAlignLeft
import androidx.compose.material.icons.automirrored.filled.FormatAlignRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techne.jenotes.presentation.ui.theme.greyText
import kotlin.math.max

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditorScreen() {
    val state = rememberRichTextState()

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 20.dp)
                .padding(bottom = it.calculateBottomPadding())
                .padding(top = it.calculateTopPadding()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            EditorToolbar(
                state = state,
                onBoldClick = { state.toggleSpanStyle(FontWeight.Bold) },
                onItalicClick = { state.toggleSpanStyle(fontStyle = FontStyle.Italic) },
                onUnderlineClick = { state.toggleSpanStyle(TextDecoration.Underline) },
                onTitleClick = { state.toggleSpanStyle(fontSize = 24.sp) },
                onSubtitleClick = { state.toggleSpanStyle(fontSize = 20.sp) },
                onTextColorClick = { state.toggleSpanStyle(color = Color.Red) },
                onStartAlignClick = { state.toggleParagraphStyle(TextAlign.Start) },
                onEndAlignClick = { state.toggleParagraphStyle(TextAlign.End) },
                onCenterAlignClick = { state.toggleParagraphStyle(TextAlign.Center) },
                onExportClick = { Log.d("Editor", state.toHtml()) }
            )
            RichTextEditor(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(top = 10.dp),
                state = state
            )
        }
    }
}

@Composable
fun EditorToolbar(
    state: RichTextState,
    onBoldClick: () -> Unit,
    onItalicClick: () -> Unit,
    onUnderlineClick: () -> Unit,
    onTitleClick: () -> Unit,
    onSubtitleClick: () -> Unit,
    onTextColorClick: () -> Unit,
    onStartAlignClick: () -> Unit,
    onEndAlignClick: () -> Unit,
    onCenterAlignClick: () -> Unit,
    onExportClick: () -> Unit,
) {
    val toolbarActions = listOf(
        ToolbarAction(Icons.Default.FormatBold, "Bold", onBoldClick),
        ToolbarAction(Icons.Default.FormatItalic, "Italic", onItalicClick),
        ToolbarAction(Icons.Default.FormatUnderlined, "Underline", onUnderlineClick),
        ToolbarAction(Icons.Default.Link, "Insert Link", onTextColorClick),
        ToolbarAction(Icons.AutoMirrored.Filled.FormatAlignLeft, "Align Left", onStartAlignClick),
        ToolbarAction(Icons.Default.FormatAlignCenter, "Align Center", onCenterAlignClick),
        ToolbarAction(Icons.AutoMirrored.Filled.FormatAlignRight, "Align Right", onEndAlignClick)
    )

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
            .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(8.dp))
            .padding(4.dp)
    ) {
        val maxWidth = constraints.maxWidth.toFloat()
        val density = LocalDensity.current // Get the current density
        val singleItemWidthPx = with(density) { 48.dp.toPx() } // Convert dp to pixels
        val maxVisibleItems = max(1, (maxWidth / singleItemWidthPx).toInt() - 2) // Subtract space for HeadingDropdown and More Options

        var expanded by remember { mutableStateOf(false) }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Dropdown for heading controls
            HeadingDropdown(onTitleClick, onSubtitleClick)

            // Display only the icons that fit, remaining ones will be moved to the overflow menu
            toolbarActions.take(maxVisibleItems).forEach { action ->
                IconToggleButton(action.icon, action.description, action.onClick)
            }

            // Overflow Menu for the remaining icons
            if (toolbarActions.size > maxVisibleItems) {
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Default.MoreVert, contentDescription = "More Options")
                }

                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    toolbarActions.drop(maxVisibleItems).forEach { action ->
                        DropdownMenuItem(
                            text = { Text(action.description) },
                            onClick = {
                                action.onClick()
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HeadingDropdown(onTitleClick: () -> Unit, onSubtitleClick: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        TextButton(onClick = { expanded = true }) {
            Text("Heading 2", style = MaterialTheme.typography.bodyMedium)
            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Heading 1") },
                onClick = {
                    onTitleClick()
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Heading 2") },
                onClick = {
                    onSubtitleClick()
                    expanded = false
                }
            )
        }
    }
}

@Composable
fun IconToggleButton(icon: ImageVector, description: String, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = icon,
            contentDescription = description,
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

data class ToolbarAction(
    val icon: ImageVector,
    val description: String,
    val onClick: () -> Unit
)


@Composable
fun RichTextEditor(modifier: Modifier = Modifier, state: RichTextState) {
    var text by remember { mutableStateOf(TextFieldValue()) }

    BasicTextField(
        value = text,
        onValueChange = { newText ->
            text = newText
            state.updateText(newText.text)
        },
        textStyle = TextStyle(
            fontSize = state.currentFontSize,
            fontWeight = state.currentFontWeight,
            fontStyle = state.currentFontStyle,
            color = state.currentColor,
            textDecoration = state.currentTextDecoration,
            textAlign = if(state.currentTextAlign != null) state.currentTextAlign!! else TextAlign.Unspecified
        ),
        modifier = modifier
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
            .padding(16.dp)
            .background(Color.White)
    )
}

@Composable
fun rememberRichTextState(): RichTextState {
    return remember { RichTextState() }
}


