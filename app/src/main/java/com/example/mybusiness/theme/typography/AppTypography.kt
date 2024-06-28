package com.example.mybusiness.theme.typography

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.mybusiness.R
import com.example.mybusiness.theme.dimens.AppDimens

fun appTypography(dims: AppDimens): Typography = Typography(
    titles = Titles(
        largeTitle = TextStyle(
            fontFamily = AppFontFamilies.Roboto,
            fontWeight = FontWeight.W500,
            fontSize = dims.textSizes.textSize32,
            lineHeight = dims.textSizes.textSize38,
        ),
        title1 = TextStyle(
            fontFamily = AppFontFamilies.Roboto,
            fontWeight = FontWeight.W500,
            fontSize = dims.textSizes.textSize20,
            lineHeight = dims.textSizes.textSize32,
            letterSpacing = dims.textSizes.textSize0_5,
        ),
    ),
    body = Body(
        body0 = TextStyle(
            fontFamily = AppFontFamilies.Roboto,
            fontWeight = FontWeight.W400,
            fontSize = dims.textSizes.textSize16,
            lineHeight = dims.textSizes.textSize20,
            letterSpacing = dims.textSizes.textSize0_5
        ),
    ),
    button = Button(
        button0 = TextStyle(
            fontFamily = AppFontFamilies.Roboto,
            fontWeight = FontWeight.W500,
            fontSize = dims.textSizes.textSize14,
            lineHeight = dims.textSizes.textSize24,
            letterSpacing = dims.textSizes.textSize0_15
        )
    ),
    subHead = SubHead(
        subhead0 = TextStyle(
            fontFamily = AppFontFamilies.Roboto,
            fontWeight = FontWeight.W400,
            fontSize = dims.textSizes.textSize14,
            lineHeight = dims.textSizes.textSize20,
        )
    )
)




@Immutable
object AppFontFamilies {
    @Stable
    val Roboto = FontFamily(
        Font(resId = R.font.roboto_regular, weight = FontWeight.W400, style = FontStyle.Normal),
        Font(resId = R.font.roboto_medium, weight = FontWeight.W500, style = FontStyle.Normal),
        Font(resId = R.font.roboto_bold, weight = FontWeight.W700, style = FontStyle.Normal),
    )
}