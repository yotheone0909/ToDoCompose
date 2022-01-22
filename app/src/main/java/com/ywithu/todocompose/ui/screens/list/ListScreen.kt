package com.ywithu.todocompose.ui.screens.list

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.ywithu.todocompose.R
import com.ywithu.todocompose.ui.theme.fabBackgroundColor
import com.ywithu.todocompose.ui.util.Action
import com.ywithu.todocompose.ui.util.SearchAppBarState
import com.ywithu.todocompose.ui.viewmodels.SharedViewModel

@ExperimentalMaterialApi
@Composable
fun ListScreen(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    LaunchedEffect(key1 = true) {
        sharedViewModel.getAllTasks()
    }

    val action : Action by sharedViewModel.action

    val allTasks by sharedViewModel.allTask.collectAsState()
    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTexState

    sharedViewModel.handleDatabaseActions(action = action)

    Scaffold(
        topBar = {
            ListAppBar(
                sharedViewModel = sharedViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
        },
        content = {
            ListContent(
                tasks = allTasks,
                navigateToTaskScreen =  navigateToTaskScreen
            )
        },
        floatingActionButton = {
            ListFub(onFubClicked = navigateToTaskScreen)
        }
    )
}

@Composable
fun ListFub(
    onFubClicked: (taskId: Int) -> Unit
) {
    FloatingActionButton(onClick = {
        onFubClicked(-1)
    }, backgroundColor = MaterialTheme.colors.fabBackgroundColor) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.add_button),
            tint = Color.White
        )
    }
}