package com.example.mybusiness.presentation.uicomponents.dragble

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.mybusiness.domain.model.ToDoModel
import com.example.mybusiness.presentation.uicomponents.listItems.ToDoItem
import kotlin.math.roundToInt

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ToDoItemDecorator(
    element: ToDoModel,
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current

    val defaultActionSize = 80.dp
    val actionSizePx = with(density) { defaultActionSize.toPx() }
    val endActionSizePx = with(density) { (defaultActionSize * 2).toPx() }

    val state = remember {
        AnchoredDraggableState(
            initialValue = DragAnchors.Center,
            anchors = DraggableAnchors {
                DragAnchors.Start at -actionSizePx
                DragAnchors.Center at 0f
                DragAnchors.End at endActionSizePx
            },
            positionalThreshold = { distance: Float -> distance * 0.5f },
            velocityThreshold = { with(density) { 100.dp.toPx() } },
            animationSpec = tween(),
        )
    }

    DraggableItem(state = state,
        startAction = {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterStart),
            ) {
                SaveAction(
                    Modifier
                        .width(defaultActionSize)
                        .fillMaxHeight()
                        .offset {
                            IntOffset(
                                ((-state
                                    .requireOffset() - actionSizePx))
                                    .roundToInt(), 0
                            )
                        }
                )
            }
        },
        endAction = {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterEnd)
                    .offset {
                        IntOffset(
                            ((-state
                                .requireOffset()) + endActionSizePx)
                                .roundToInt(), 0
                        )
                    }
            )
            {
                DeleteAction(
                    Modifier
                        .width(defaultActionSize)
                        .fillMaxHeight()
                )
            }
        }, content = {
            ToDoItem(element = element, modifier = modifier)
        })
}

enum class DragAnchors {
    Start,
    Center,
    End,
}