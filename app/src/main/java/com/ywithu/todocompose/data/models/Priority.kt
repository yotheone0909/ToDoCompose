package com.ywithu.todocompose.data.models

import androidx.compose.ui.graphics.Color
import com.ywithu.todocompose.ui.theme.HighPriorityColor
import com.ywithu.todocompose.ui.theme.LowPriorityColor
import com.ywithu.todocompose.ui.theme.MediumPriorityColor
import com.ywithu.todocompose.ui.theme.NonePriorityColor

enum class  Priority(val color : Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}