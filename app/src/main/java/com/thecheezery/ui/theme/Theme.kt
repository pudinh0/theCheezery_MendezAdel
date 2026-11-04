package com.thecheezery.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val Colors = lightColorScheme(
    primary = Very_purple,
    onPrimary = androidx.compose.ui.graphics.Color.White,
    secondary = Pinky,
    background = androidx.compose.ui.graphics.Color(0xFFFCFAFC),
    surface = androidx.compose.ui.graphics.Color(0xFFFCFAFC),
    onSurface = DarkGrey,
)

@Composable
fun CheezeryTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = Colors, content = content)
}
