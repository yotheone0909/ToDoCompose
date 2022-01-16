package com.ywithu.todocompose.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.ywithu.todocompose.navigation.destinations.listComposable
import com.ywithu.todocompose.navigation.destinations.taskComposable
import com.ywithu.todocompose.ui.util.Constants.LIST_SCREEN
import com.ywithu.todocompose.ui.viewmodels.SharedViewModel

@ExperimentalMaterialApi
@Composable
fun SetupNavigation(
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel
) {

    val screen = remember(navHostController) {
        Screens(navHostController = navHostController)
    }

    NavHost(navController = navHostController, startDestination = LIST_SCREEN) {
        listComposable(
            navigateToTaskScreen = screen.task,
            sharedViewModel = sharedViewModel
        )

        taskComposable(
            navigateToListScreen = screen.list
        )
    }
}