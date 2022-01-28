package com.ywithu.todocompose.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.ywithu.todocompose.ui.screens.splash.SplashScreen
import com.ywithu.todocompose.ui.util.Constants

@ExperimentalAnimationApi
fun NavGraphBuilder.splashComposable(
    navigateToTaskScreen: () -> Unit
) {
    composable(
        route = Constants.SPLASH_SCREEN,
        exitTransition = {
            slideOutVertically(
                targetOffsetY = { fullHeight ->
                    -fullHeight
                },
                animationSpec = tween(300)
            )
        }
    ) {
        SplashScreen(navigateToListScreen = navigateToTaskScreen)
    }
}