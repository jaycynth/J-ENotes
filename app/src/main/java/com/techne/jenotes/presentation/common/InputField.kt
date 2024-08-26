package com.techne.jenotes.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techne.jenotes.presentation.ui.theme.bgColor
import com.techne.jenotes.presentation.ui.theme.fieldColor
import com.techne.jenotes.presentation.ui.theme.plusJakartaSans

@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: ImageVector?,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {

    TextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(color = bgColor, fontWeight = FontWeight.Bold),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(8.dp),
        leadingIcon = if (leadingIcon != null) {
            {
                Icon(
                    imageVector = leadingIcon,
                    tint = bgColor,
                    contentDescription = "$label icon"
                )
            }
        } else null,
        label = {
            Text(
                text = label,
                color = bgColor,
                style = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.Bold)
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = visualTransformation,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = fieldColor,
            unfocusedContainerColor = fieldColor,
            disabledContainerColor = fieldColor,
            cursorColor = bgColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        )
    )
}

@Composable
fun PasswordInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPasswordVisible: Boolean,
    leadingIcon: ImageVector?,
    onPasswordVisibilityToggle: () -> Unit,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        textStyle = TextStyle(color = bgColor, fontWeight = FontWeight.Bold),
        label = {
            Text(
                text = label,
                color = bgColor,
                style = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.Bold)
            )
        },
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        leadingIcon = if (leadingIcon != null) {
            {
                Icon(
                    imageVector = leadingIcon,
                    tint = bgColor,
                    contentDescription = "$label icon"
                )
            }
        } else null,
        trailingIcon = {
            val image =
                if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
            IconButton(onClick = onPasswordVisibilityToggle) {
                Icon(
                    imageVector = image,
                    contentDescription = "Toggle password visibility",
                    tint = bgColor,
                    modifier = Modifier.size(20.dp)
                )
            }
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = fieldColor,
            unfocusedContainerColor = fieldColor,
            disabledContainerColor = fieldColor,
            cursorColor = bgColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        )
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(8.dp),
        label = {
            Text(
                text = label,
                color = Color.White,
                style = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.ExtraLight)
            )
        },        leadingIcon = leadingIcon,
        visualTransformation = visualTransformation,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            cursorColor = Color.White,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White,
            disabledIndicatorColor = Color.White,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.White,
        )
    )
}
