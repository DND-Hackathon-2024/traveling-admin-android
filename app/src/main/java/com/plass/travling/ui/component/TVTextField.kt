package com.plass.travling.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.plass.travling.R
import com.plass.travling.ui.theme.TravelingTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TVTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String = "",
    enabled: Boolean = true,
    secured: Boolean = false,
    singleLine: Boolean = true,
    textStyle: TextStyle = TravelingTheme.typography.bodyMedium,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: TextFieldColors = TextFieldDefaults.colors(
//        focusedTextColor = TravelingTheme.colorScheme.,
        focusedContainerColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
//        unfocusedTextColor = TravelingTheme.colorScheme.textNormal,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
//        disabledTextColor = TravelingTheme.colorScheme.textFieldTextDisabled,
        disabledContainerColor = Color.Transparent,
    ),
) {
    var isFocused by remember { mutableStateOf(false) }
    var showText by remember { mutableStateOf(false) }
    val isSecured = secured && !showText

    BasicTextField(
        value = value,
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.Transparent,
                shape = shape,
            )
            .onFocusChanged {
                isFocused = it.isFocused
            }
            .border(
                width = 1.dp,
                color = TravelingTheme.colorScheme.Gray,
                shape = shape,
            ),
        onValueChange = onValueChange,
        enabled = enabled,
        textStyle = textStyle,
        singleLine = singleLine,
        keyboardOptions = if (isSecured) KeyboardOptions.Default else KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (isSecured) PasswordVisualTransformation() else VisualTransformation.None,
        decorationBox = @Composable { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = value,
                innerTextField = innerTextField,
                placeholder = {
                    Text(
                        text = hint,
                        style = textStyle,
                        color = TravelingTheme.colorScheme.Black
                    )
                },
                label = null,
                contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(
                    start = 12.dp,
                    end = 8.dp,
                    top = 14.dp,
                    bottom = 14.dp
                ),
                shape = shape,
                enabled = enabled,
                colors = colors,
                interactionSource = remember { MutableInteractionSource() },
                singleLine = false,
                visualTransformation = VisualTransformation.None,
            )
        },
    )
}
