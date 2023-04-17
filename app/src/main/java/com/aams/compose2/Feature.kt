package com.aams.compose2

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class Feature (
    val title :String,
    @DrawableRes val iconId : Int,
    val light :Color,
    val mid :Color,
    val dark :Color

)
