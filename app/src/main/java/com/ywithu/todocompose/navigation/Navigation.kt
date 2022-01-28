package com.ywithu.todocompose.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.ywithu.todocompose.navigation.destinations.listComposable
import com.ywithu.todocompose.navigation.destinations.splashComposable
import com.ywithu.todocompose.navigation.destinations.taskComposable
import com.ywithu.todocompose.ui.util.Constants.SPLASH_SCREEN
import com.ywithu.todocompose.ui.viewmodels.SharedViewModel

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun SetupNavigation(
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel
) {

    val screen = remember(navHostController) {
        Screens(navHostController = navHostController)
    }

    AnimatedNavHost(navController = navHostController, startDestination = SPLASH_SCREEN) {
        splashComposable(
            navigateToTaskScreen = screen.splash
        )

        listComposable(
            navigateToTaskScreen = screen.list,
            sharedViewModel = sharedViewModel
        )

        taskComposable(
            navigateToListScreen = screen.task,
            sharedViewModel = sharedViewModel
        )
    }
}