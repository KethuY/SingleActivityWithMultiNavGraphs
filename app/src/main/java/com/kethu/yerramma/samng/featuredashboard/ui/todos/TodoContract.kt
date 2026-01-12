package com.kethu.yerramma.samng.featuredashboard.ui.todos

import com.kethu.yerramma.samng.base.BaseUiState
import com.kethu.yerramma.samng.featuredashboard.uimodels.TodoUiModel

sealed class TodoEvent {
    data object GetTodoItems : TodoEvent()
}

data class TodoUiState(
    val isLoading: Boolean = false,
    val items: List<TodoUiModel> = emptyList()
) : BaseUiState