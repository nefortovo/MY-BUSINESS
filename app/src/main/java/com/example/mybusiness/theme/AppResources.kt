package com.example.mybusiness.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import com.example.mybusiness.theme.colors.AppColors
import com.example.mybusiness.theme.colors.LocalAppColors
import com.example.mybusiness.theme.colors.appColors
import com.example.mybusiness.theme.dimens.AppDimens
import com.example.mybusiness.theme.dimens.LocalAppDimens
import com.example.mybusiness.theme.dimens.appDimens
import com.example.mybusiness.theme.typography.LocalAppTypography
import com.example.mybusiness.theme.typography.Typography
import com.example.mybusiness.theme.typography.appTypography

object AppResources {
    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalAppColors.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalAppTypography.current

    val dimens: AppDimens
        @Composable
        @ReadOnlyComposable
        get() = LocalAppDimens.current
}

@Composable
fun CustomAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val dimensions: AppDimens = appDimens()
    val ourColors: AppColors = appColors(darkTheme)
    val appTypography: Typography = appTypography(dimensions)

    MaterialTheme(
        colorScheme = if (darkTheme) darkColorScheme() else lightColorScheme()
    ) {
        CompositionLocalProvider(
            LocalAppColors provides ourColors,
            LocalAppTypography provides appTypography,
            LocalAppDimens provides dimensions,
            content = content
        )
    }
}