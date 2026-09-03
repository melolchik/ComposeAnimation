package ru.melolchik.composeanimation.ui.screen

import androidx.compose.animation.VectorConverter
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun AnimatableExample() {
    val coroutineScope = rememberCoroutineScope()
    val offsetX = remember { Animatable(0f) }
    val boxColor = remember { Animatable(Color.Red,
        Color.VectorConverter(Color.Red.colorSpace)) }

    // Обработчик нажатия
    fun animateBox() {
        coroutineScope.launch {
            // Анимация перемещения по оси X
            offsetX.animateTo(
                targetValue = 100f,
                animationSpec = tween(600)
            )

            // Анимация изменения цвета
            boxColor.animateTo(
                targetValue = Color.Green,
                animationSpec = tween(300)
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { animateBox() },
        contentAlignment = Alignment.Center
    ) {
        // Применение анимируемого значения для смещения и цвета
        Box(
            modifier = Modifier
                .size(100.dp)
                .offset(offsetX.value.dp, 0.dp)
                .background(boxColor.value)
        ) {
            Text("Нажми на меня")
        }
    }
}