package com.ywithu.todocompose.ui.screens.task

import android.content.Context
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.ywithu.todocompose.data.models.Priority
import com.ywithu.todocompose.data.models.ToDoTask
import com.ywithu.todocompose.ui.util.Action
import com.ywithu.todocompose.ui.viewmodels.SharedViewModel

@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit
) {

    val title: String by sharedViewModel.title
    val description: String by sharedViewModel.description
    val priority: Priority by sharedViewModel.priority

    val context = LocalContext.current

//    BackHandler(onBackPressed = {
//        navigateToListScreen(Action.NO_ACTION)
//    })
    BackHandler {
        navigateToListScreen(Action.NO_ACTION)
    }

    Scaffold(
        topBar = {
            TaskAppBar(
                selectedTask = selectedTask,
                navigateToListScreen = { action ->
                    if (action == Action.NO_ACTION) {
                        navigateToListScreen(action)
                    } else {
                        if (sharedViewModel.validateFields()) {
                            navigateToListScreen(action)
                        } else {
                            displayToast(context = context)
                        }
                    }
                }
            )
        },
        content = {
            TaskContent(
                title = title,
                onTitleChange = {
                    sharedViewModel.updateTitle(it)
                },
                description = description,
                onDescriptionChange = {
                    sharedViewModel.description.value = it
                },
                priority = priority,
                onPrioritySelected = {
                    sharedViewModel.priority.value = it
                }
            )
        }
    )

}

fun displayToast(context: Context) {
    Toast.makeText(
        context,
        "Fields Empty.",
        Toast.LENGTH_SHORT
    ).show()
}

//@Composable
//fun BackHandler(
//    backDispatcher: OnBackPressedDispatcher? = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher,
//    onBackPressed: () -> Unit
//) {
//
//    val currentOnBackPressed by rememberUpdatedState(newValue = onBackPressed)
//
//    val backCallBack = remember {
//        object : OnBackPressedCallback(true) {
//
//            override fun handleOnBackPressed() {
//                currentOnBackPressed()
//            }
//        }
//    }
//
//    DisposableEffect(key1 = backDispatcher) {
//        backDispatcher?.addCallback(backCallBack)
//
//        onDispose {
//            backCallBack.remove()
//        }
//    }
//}
