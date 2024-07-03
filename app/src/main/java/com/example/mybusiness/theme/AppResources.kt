package com.example.mybusiness.theme

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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


@Preview
@Composable
fun TypographyPreview() {
    CustomAppTheme {
        val modifier = Modifier.padding(all = 15.dp)
        Surface(color = AppResources.colors.BackSecondary) {
            Column {
                Text(
                    text = "Large title — 32/38",
                    style = AppResources.typography.titles.largeTitle,
                    modifier = modifier
                )
                Text(
                    text = "Title — 20/32",
                    style = AppResources.typography.titles.title1,
                    modifier = modifier
                )
                Text(
                    text = "BUTTON — 14/24",
                    style = AppResources.typography.button.button0,
                    modifier = modifier
                )
                Text(
                    text = "Body — 16/20",
                    style = AppResources.typography.body.body0,
                    modifier = modifier
                )
                Text(
                    text = "Subhead — 14/20",
                    style = AppResources.typography.subHead.subhead0,
                    modifier = modifier
                )
            }
        }
    }
}

@Preview(device = "spec:width=4000dp,height=400dp")
@Preview(
    device = "spec:width=4000dp,height=400dp",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ColorPreview() {
    CustomAppTheme {
        val colors = mapOf(
            "Support / Separator #33000000" to AppResources.colors.SupportSeparator,
            "Support / Overlay #0F000000" to AppResources.colors.SupportOverlay,
            "Label / Primary #000000" to AppResources.colors.LabelPrimary,
            "Label / Secondary #99000000" to AppResources.colors.LabelSecondary,
            "Label / Tertiary #4D000000" to AppResources.colors.LabelTertiary,
            "Label / Disable #26000000" to AppResources.colors.LabelDisable,
            "Color / Red #FF3B30" to AppResources.colors.Red,
            "Color / Green #34C759" to AppResources.colors.Green,
            "Color / Blue #007AFF" to AppResources.colors.Blue,
            "Color / Gray #8E8E93" to AppResources.colors.Gray,
            "Color /Gray Light #D1D1D6" to AppResources.colors.GrayLight,
            "Color / White #FFFFFF" to AppResources.colors.White,
            "Back / Primary #F7F6F2" to AppResources.colors.BackPrimary,
            "Back / Secondary #FFFFFF" to AppResources.colors.BackSecondary,
            "Back / Elevated #FFFFFF" to AppResources.colors.BackElevated,
        )
        Row {
            colors.forEach { (colorName, color) ->
                ColorBox(color = color, text = colorName)
            }
        }
    }
}


@Composable
fun ColorBox(color: Color, text: String) {
    Box(
        modifier = Modifier
            .size(150.dp)
            .background(color = color)
    ) {
        Text(
            text = text,
            color = if (color.luminance() > 0.5f) Color.Black else Color.White,
            modifier = Modifier.padding(8.dp)
        )
    }
}
