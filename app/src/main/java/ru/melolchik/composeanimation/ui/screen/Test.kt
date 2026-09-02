package ru.melolchik.composeanimation.ui.screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Test() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(
                state = rememberScrollState(),
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var isIncreased : Boolean by remember {
            mutableStateOf(true)
        }

        val size = animateDpAsState(
            targetValue = if(isIncreased) 200.dp else 100.dp,
            animationSpec = infiniteRepeatable(
                animation = tween (3000),
                repeatMode = RepeatMode.Reverse
            )
        )

        val infiniteTransition = rememberInfiniteTransition()

        val transitionSize by infiniteTransition.animateFloat(
            initialValue = 200f,
            targetValue = 100f,
            animationSpec = infiniteRepeatable(
                animation = tween (3000),
                repeatMode = RepeatMode.Reverse
            )
        )

        var isCircle : Boolean by remember {
            mutableStateOf(false)
        }

        val cornerSizeState by animateIntAsState(
            if(isCircle) 50 else 8
        )

        var isSelected : Boolean by remember {
            mutableStateOf(false)
        }

        val borderSize by animateDpAsState(
            if(isSelected) 4.dp else 0.dp
        )

        var isSelectedColor : Boolean by remember {
            mutableStateOf(false)
        }

        val colorState by animateColorAsState(
            if(isSelectedColor) Color.Magenta else Color.Blue
        )

        var isVisible : Boolean by remember {
            mutableStateOf(true)
        }

        val alphaState by animateFloatAsState(
            if(isVisible) 1f else 0f
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                isIncreased = !isIncreased
            }
        ) {
            Text(
                text = "Animate size",
            )
        }
        AnimatedContainer(
            size = transitionSize.dp ,
            text = "Size"
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { isCircle = !isCircle}
        ) {
            Text(
                text = "Animate shape",
            )
        }
        AnimatedContainer(
            text = "Shape",
            cornerSizePercent = cornerSizeState
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                isSelected = !isSelected
            }
        ) {
            Text(
                text = "Animate border",
            )
        }
        AnimatedContainer(
            text = "Border",
            borderWidth = borderSize
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { isSelectedColor = !isSelectedColor}
        ) {
            Text(
                text = "Animate color",
            )
        }
        AnimatedContainer(
            text = "Color",
            color = colorState
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {isVisible = !isVisible}
        ) {
            Text(
                text = "Animate visibility",
            )
        }
        AnimatedContainer(
            text = "Visibility",
            alpha = alphaState
        )
    }
}

@Composable
private fun AnimatedContainer(
    size : Dp = 200.dp,
    text: String,
    cornerSizePercent: Int = 8,
    borderWidth : Dp = 0.dp,
    color : Color = Color.Blue,
    alpha : Float = 1f

) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(cornerSizePercent))
            .alpha(alpha = alpha)
            .background(color)
            .border(width = borderWidth, color = Color.Black)
            .size(size),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White
        )
    }
}