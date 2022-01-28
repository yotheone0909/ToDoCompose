package com.ywithu.todocompose.navigation

import androidx.navigation.NavHostController
import com.ywithu.todocompose.ui.util.Action
import com.ywithu.todocompose.ui.util.Constants.LIST_SCREEN
import com.ywithu.todocompose.ui.util.Constants.SPLASH_SCREEN

class Screens(navHostController: NavHostController) {
    val splash: () -> Unit = {
        navHostController.navigate(route = "list/${Action.NO_ACTION.name}") {
            popUpTo(SPLASH_SCREEN) { inclusive = true }
        }
    }
    val list: (Int) -> Unit = { taskId ->
        navHostController.navigate("task/$taskId")
    }
    val task: (Action) -> Unit = { action ->
        navHostController.navigate("list/${action.name}") {
            popUpTo(LIST_SCREEN) { inclusive = true }
        }
    }
}