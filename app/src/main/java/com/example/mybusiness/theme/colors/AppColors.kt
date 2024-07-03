package com.example.mybusiness.theme.colors

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
sealed class AppColors(
    val SupportOverlay: Color,
    val SupportSeparator: Color,
    val LabelPrimary: Color,
    val LabelSecondary: Color,
    val LabelDisable: Color,
    val LabelTertiary: Color,
    val BackPrimary: Color,
    val BackSecondary: Color,
    val BackElevated: Color,
    val Red: Color,
    val Green: Color,
    val Blue: Color,
    val Gray: Color,
    val GrayLight: Color,
    val White: Color,


    ) {
    class LightThemeAppColors : AppColors(
        SupportOverlay =SupportOverlayLight,
        SupportSeparator =SupportSeparatorLight,
        LabelPrimary =LabelPrimaryLight,
        LabelSecondary =LabelSecondaryLight,
        LabelDisable =LabelDisableLight,
        LabelTertiary =LabelTertiaryLight,
        BackPrimary =BackPrimaryLight,
        BackSecondary =BackSecondaryLight,
        BackElevated =BackElevatedLight,
        GrayLight =GrayLightL,
        Red = Red,
        Green = Green,
        Blue = Blue,
        Gray = Gray,
        White = White,
    )

    class DarkThemeAppColors : AppColors(
        SupportOverlay =SupportOverlayDark,
        SupportSeparator =SupportSeparatorDark,
        LabelPrimary =LabelPrimaryDark,
        LabelSecondary =LabelSecondaryDark,
        LabelDisable =LabelDisableDark,
        LabelTertiary =LabelTertiaryDark,
        BackPrimary =BackPrimaryDark,
        BackSecondary =BackSecondaryDark,
        BackElevated =BackElevatedDark,
        GrayLight = GrayLightD,
        Red = Red,
        Green = Green,
        Blue = Blue,
        Gray = Gray,
        White = White,
    )
}


fun appColors(darkTheme: Boolean): AppColors = when (darkTheme) {
    true -> AppColors.DarkThemeAppColors()
    false -> AppColors.LightThemeAppColors()
}


val LocalAppColors = staticCompositionLocalOf<AppColors> {
    error("No AppColors provided")
}

