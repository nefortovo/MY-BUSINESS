package com.example.mybusiness.theme.typography

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle

data class Typography(
    val titles: Titles,
    val body: Body,
    val button: Button,
    val subHead: SubHead,
)

data class Titles(
    val largeTitle: TextStyle,
    val title1: TextStyle,
)

data class Body(
    val body0: TextStyle,
)

data class Button(
    val button0: TextStyle
)

data class SubHead(
    val subhead0: TextStyle
)

val LocalAppTypography = staticCompositionLocalOf<Typography> {
    error("No AppTypography provided")
}