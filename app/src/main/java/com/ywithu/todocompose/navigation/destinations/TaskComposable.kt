package com.ywithu.todocompose.navigation.destinations

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ywithu.todocompose.ui.util.Action
import com.ywithu.todocompose.ui.util.Constants

fun NavGraphBuilder.taskComposable(
    navigateToListScreen :(Action) -> Unit
) {
    composable(
        route = Constants.TASK_SCREEN,
        arguments = listOf(navArgument(Constants.TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) { navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(Constants.TASK_ARGUMENT_KEY)
        Log.d("TaskComposable", taskId.toString())

    }
}