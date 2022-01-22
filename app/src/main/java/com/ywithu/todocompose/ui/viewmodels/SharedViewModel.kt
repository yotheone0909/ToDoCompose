package com.ywithu.todocompose.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ywithu.todocompose.data.models.Priority
import com.ywithu.todocompose.data.models.ToDoTask
import com.ywithu.todocompose.data.repositories.ToDoRepository
import com.ywithu.todocompose.ui.util.Action
import com.ywithu.todocompose.ui.util.Constants.MAX_TITLE_LENGTH
import com.ywithu.todocompose.ui.util.RequestState
import com.ywithu.todocompose.ui.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: ToDoRepository
) : ViewModel() {

    val action: MutableState<Action> = mutableStateOf(Action.NO_ACTION)

    val id: MutableState<Int> = mutableStateOf(0)
    val title: MutableState<String> = mutableStateOf("")
    val description: MutableState<String> = mutableStateOf("")
    val priority: MutableState<Priority> = mutableStateOf(Priority.LOW)

    val searchAppBarState: MutableState<SearchAppBarState> =
        mutableStateOf(SearchAppBarState.CLOSED)
    val searchTexState: MutableState<String> = mutableStateOf("")

    private val _allTask =
        MutableStateFlow<RequestState<List<ToDoTask>>>(RequestState.Idle)

    val allTask: StateFlow<RequestState<List<ToDoTask>>> = _allTask

    fun getAllTasks() {
        Log.d("ListScreen", "getAllTasks")
        _allTask.value = RequestState.Loading
        viewModelScope.launch {
            try {
                repository.getAllTask
                    .collect {
                        _allTask.value = RequestState.Success(it)
                    }
            } catch (e: Exception) {
                _allTask.value = RequestState.Error(e)
            }
        }
    }

    private val _selectedTask: MutableStateFlow<ToDoTask?> = MutableStateFlow(null)
    val selectedTask: StateFlow<ToDoTask?> = _selectedTask;

    fun getSelectedTask(taskId: Int) {
        viewModelScope.launch {
            repository.getSelectTask(taskId = taskId).collect { task ->
                _selectedTask.value = task
            }
        }
    }

    private fun addTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val todoTask = ToDoTask(
                title = title.value,
                description = description.value,
                priority = priority.value
            )
            repository.addTask(toDoTask = todoTask)
        }
    }

    fun handleDatabaseActions(action: Action) {
        when (action) {
            Action.ADD -> {
                addTask()
            }
            Action.UPDATE -> {

            }
            Action.DELETE -> {

            }
            Action.DELETE_ALL -> {

            }
            Action.UNDO -> {

            }
            else -> {

            }
        }
        this.action.value = Action.NO_ACTION
    }

    fun updateTaskFields(selectedTodoTask: ToDoTask?) {
        if (selectedTodoTask != null) {
            id.value = selectedTodoTask.id
            title.value = selectedTodoTask.title
            description.value = selectedTodoTask.description
            priority.value = selectedTodoTask.priority
        } else {
            id.value = 0
            title.value = ""
            description.value = ""
            priority.value = Priority.LOW
        }
    }

    fun updateTitle(newTitle: String) {
        if (newTitle.length < MAX_TITLE_LENGTH) {
            title.value = newTitle
        }
    }

    fun validateFields(): Boolean {
        return title.value.isNotEmpty() && description.value.isNotEmpty()
    }
}