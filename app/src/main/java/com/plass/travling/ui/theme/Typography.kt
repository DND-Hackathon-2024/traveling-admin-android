package com.plass.travling.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle

@Immutable
class TravelingTypography(
    val title1B: TextStyle = TypographyTokens.Title1B,
    val title1M: TextStyle = TypographyTokens.Title1M,
    val title1R: TextStyle = TypographyTokens.Title1R,
    val title2B: TextStyle = TypographyTokens.Title2B,
    val title2M: TextStyle = TypographyTokens.Title2M,
    val title2R: TextStyle = TypographyTokens.Title2R,
    val headline1B: TextStyle = TypographyTokens.Headline1B,
    val headline1M: TextStyle = TypographyTokens.Headline1M,
    val headline1R: TextStyle = TypographyTokens.Headline1R,
    val headline2B: TextStyle = TypographyTokens.Headline2B,
    val headline2M: TextStyle = TypographyTokens.Headline2M,
    val headline2R: TextStyle = TypographyTokens.Headline2R,
    val bodyBold: TextStyle = TypographyTokens.BodyBold,
    val bodyMedium: TextStyle = TypographyTokens.BodyMedium,
    val bodyRegular: TextStyle = TypographyTokens.BodyRegular,
    val labelBold: TextStyle = TypographyTokens.LabelBold,
    val labelMedium: TextStyle = TypographyTokens.LabelMedium,
    val labelRegular: TextStyle = TypographyTokens.LabelRegular,
    val captionBold: TextStyle = TypographyTokens.CaptionBold,
    val captionMedium: TextStyle = TypographyTokens.CaptionMedium,
    val captionRegular: TextStyle = TypographyTokens.CaptionRegular,
)

internal val LocalTravelingTypography = staticCompositionLocalOf { TravelingTypography() }