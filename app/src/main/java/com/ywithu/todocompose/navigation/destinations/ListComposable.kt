package com.ywithu.todocompose.navigation.destinations

import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ywithu.todocompose.ui.screens.list.ListScreen
import com.ywithu.todocompose.ui.util.Constants.LIST_ARGUMENT_KEY
import com.ywithu.todocompose.ui.util.Constants.LIST_SCREEN
import com.ywithu.todocompose.ui.viewmodels.SharedViewModel

@ExperimentalMaterialApi
fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) {
        ListScreen(
            navigateToTaskScreen = navigateToTaskScreen,
            sharedViewModel = sharedViewModel
        )
    }
}