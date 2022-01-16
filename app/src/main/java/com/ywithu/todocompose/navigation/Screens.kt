package com.ywithu.todocompose.navigation

import androidx.navigation.NavHostController
import com.ywithu.todocompose.ui.util.Action
import com.ywithu.todocompose.ui.util.Constants.LIST_SCREEN

class Screens(navHostController: NavHostController) {
    val list: (Action) -> Unit = { action ->
        navHostController.navigate("list/${action.name}") {
            popUpTo(LIST_SCREEN) { inclusive = true}
        }
    }
    val task : (Int) -> Unit = { taskId ->
        navHostController.navigate("task/$taskId")
    }
}