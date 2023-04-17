package com.aams.compose2.ui.theme

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aams.compose2.Feature
import com.aams.compose2.R
import androidx.compose.foundation.Canvas
import kotlin.math.abs

fun Path.standardQuadFromTo(from: Offset, to: Offset) {
    quadraticBezierTo(
        from.x,
        from.y,
        abs(from.x + to.x) / 2f,
        abs(from.y + to.y) / 2f
    )
}

data class BottomMenuContent(
    val title: String,
    @DrawableRes val iconId: Int
)

@ExperimentalFoundationApi
@Preview
@Composable
fun homeScreen() {
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            greting()
            Chips(
                chips = listOf(
                    "Sweet sleep",
                    "Insomnia",
                    "Depression",
                    "calm night",
                    "relax",
                    "bad day"
                )
            )
            currentm()
            Features(
                features = listOf(
                    Feature(
                        "Sleep mediation", R.drawable.ic_baseline_music_note_24, OrangeYellow3,
                        OrangeYellow2, OrangeYellow1
                    ),
                    Feature(
                        "Tips for sleeping", R.drawable.ic_baseline_ondemand_video_24, LightGreen1,
                        LightGreen2, LightGreen3
                    ),
                    Feature(
                        "Night island", R.drawable.ic_baseline_headphones_24, Beige1,
                        Beige2, Beige3
                    ),
                    Feature(
                        "Calming sounds", R.drawable.ic_baseline_headphones_24, BlueViolet1,
                        BlueViolet2, BlueViolet3
                    )


                )

            )

        }
        BottomMenu(items = listOf(
            BottomMenuContent("Home", R.drawable.ic_baseline_home_24),
            BottomMenuContent("Meditate", R.drawable.ic_baseline_bubble_chart_24),
            BottomMenuContent("Sleep", R.drawable.ic_noon_24),
            BottomMenuContent("Music", R.drawable.ic_baseline_music_note_24),
            BottomMenuContent("Profile", R.drawable.ic_baseline_account_circle_24)
        ), modifier = Modifier.align(Alignment.BottomCenter))
    }


}

@Composable
fun greting(name: String = "mohamed") {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(horizontalAlignment = Alignment.Start) {
            Text(text = "Good morning \n$name", style = Typography.h4, color = Color.White)
            Text(
                text = "we wish you are having a great day",
                style = Typography.body1,
                color = Color.White
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_search_24),
            contentDescription = "search", tint = Color.White, modifier = Modifier
                .size(50.dp)
                .padding(top = 20.dp)

        )
    }
}

@Composable
fun Chips(chips: List<String>) {
    var selected by remember {
        mutableStateOf(0)
    }
    LazyRow {
        items(chips.size) {
            Box(

                modifier =
                Modifier
                    .padding(start = 15.dp, top = 15.dp, end = 15.dp)
                    .clickable { selected = it }
                    .clip(RoundedCornerShape(20))
                    .background(
                        if (selected == it) {
                            ButtonBlue
                        } else {
                            DarkerButtonBlue
                        }
                    )
                    .padding(15.dp), contentAlignment = Alignment.Center
            ) {
                Text(text = chips[it], color = TextWhite)
            }


        }
    }
}

@Composable
fun currentm(color: Color = LightRed) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = "Daily Thought",
                style = typography.h4, color = TextWhite

            )
            Text(
                text = "Meditation • 3-10 min",
                style = typography.body1,
                color = TextWhite
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .padding(5.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_play_circle_24),
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun Features(features: List<Feature>) {
    Column {
        Text(
            "Featured", style = typography.h3, modifier = Modifier.padding(15.dp), color = TextWhite
        )
        LazyVerticalGrid(

            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(15.dp, bottom = 20.dp),
            modifier = Modifier
                .fillMaxHeight()
                .padding(bottom = 100.dp)
        ) {
            items(features.size) {
                com.aams.compose2.ui.theme.item(feature = features[it])
            }
        }

        }

        }



@Composable
fun item(feature: Feature) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(feature.dark)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        val color1 = Offset(0f, height * 0.3f)
        val color2 = Offset(width * 0f, height * 0.35f)
        val color3 = Offset(0.4f * width, height * 0.05f)
        val color4 = Offset(width * 0.75f, height * 0.7f)
        val color5 = Offset(width * 1.4f, height.toFloat())

        val mediumColoredPath = Path().apply {
            moveTo(color1.x, color1.y)
            standardQuadFromTo(color1, color2)
            standardQuadFromTo(color2, color3)
            standardQuadFromTo(color3, color4)
            standardQuadFromTo(color4, color5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()

        }
        val lightPoint1 = Offset(0f, height * 0.35f)
        val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
        val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)
        val lightColoredPath = Path().apply {
            moveTo(color1.x, color1.y)
            standardQuadFromTo(lightPoint1, lightPoint2)
            standardQuadFromTo(lightPoint2, lightPoint3)
            standardQuadFromTo(lightPoint3, lightPoint4)
            standardQuadFromTo(lightPoint4, lightPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            drawPath(
                path = mediumColoredPath,
                color = feature.mid
            )
            drawPath(
                path = lightColoredPath,
                color = feature.light
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(7.5.dp)
        ) {
            Text(
                text = feature.title,
                style = typography.h4,
                color = TextWhite,
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.TopStart)
            )
            Icon(
                painter = painterResource(id = feature.iconId),
                contentDescription = feature.title,
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .size(40.dp)
            )
            Text(
                text = "Start",
                color = TextWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {
                        // Handle the click
                    }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(ButtonBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }

    }
}

@Composable
fun BottomMinuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemClick: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onItemClick()
        }) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) activeHighlightColor else Color.Transparent)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = item.title,
            color = if(isSelected) activeTextColor else inactiveTextColor
        )
    }

}

@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    initialSelectedItemIndex: Int = 0
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(15.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomMinuItem(
                item = item,
                isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor
            ) {
                selectedItemIndex = index
            }
        }
    }
}



