package com.kethu.yerramma.samng.featuredashboard.ui.todos

import androidx.lifecycle.viewModelScope
import com.kethu.yerramma.samng.base.BaseUiEffect
import com.kethu.yerramma.samng.base.BaseUiError
import com.kethu.yerramma.samng.base.ComposeBaseViewModel
import com.kethu.yerramma.samng.featuredashboard.uimodels.TodoUiModel
import com.kethu.yerramma.samng.networkmodule.client.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * @Author: Yerramma Kethu
 */
@HiltViewModel
class TodoViewModel @Inject constructor(private val interactor: TodosInteractor) :
    ComposeBaseViewModel<TodoEvent, TodoUiState, BaseUiEffect, BaseUiError>() {

    override fun onAction(action: TodoEvent) {
        if (action == TodoEvent.GetTodoItems) {
            fetchTodos()
        }
    }

    private fun fetchTodos() {

        interactor.fetchTodos().onEach { response ->
            sendUiState(state.value.copy(isLoading = response.apiNetworkState() == NetworkState.LOADING))
            when (response.apiNetworkState()) {
                NetworkState.SUCCESS -> {
                    response.getResponse<List<TodoUiModel>>()?.let { items ->
                        sendUiState(state.value.copy(items = items))
                    }
                }

                NetworkState.FAILED -> {
                    sendUiError(BaseUiError.ShowErrorDialog(response.getError()))
                }
            }
        }.launchIn(viewModelScope)
    }

    override fun defaultState(): TodoUiState = TodoUiState()

}