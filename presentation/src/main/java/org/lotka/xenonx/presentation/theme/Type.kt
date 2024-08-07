package org.lotka.xenonx.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.lotka.xenonx.presentation.R

// Set of Material typography styles to start with
val quickSand = FontFamily(
     Font(R.font.quicksand_light , FontWeight.Light),
     Font(R.font.quicksand_regular , FontWeight.Normal),
     Font(R.font.quicksand_semibold , FontWeight.SemiBold),
     Font(R.font.quicksand_bold , FontWeight.Bold),
     Font(R.font.quicksand_medium , FontWeight.Medium)
    )

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = quickSand,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp

    ))

    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
