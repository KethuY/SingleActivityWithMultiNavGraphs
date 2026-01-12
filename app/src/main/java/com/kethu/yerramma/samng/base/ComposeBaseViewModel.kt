package com.kethu.yerramma.samng.base

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * @Author: Yerramma Kethu
 * @Date: 15/12/2025
 */
abstract class ComposeBaseViewModel<Action, State : BaseUiState, Effect : BaseUiEffect, Error : BaseUiError> :
    BaseViewModel() {

    abstract fun onAction(action: Action)
    abstract fun defaultState(): State

    private val _uiEffect = Channel<Effect>()
    val uiEffect = _uiEffect.receiveAsFlow()

    private val _uiError = Channel<Error>()
    val uiError = _uiError.receiveAsFlow()

    private val _state = MutableStateFlow(defaultState())
    val state = _state.asStateFlow()

    protected fun sendUiState(uiState: State) {
        viewModelScope.launch {
            _state.emit(uiState)
        }
    }

    protected fun sendUiEffect(uiEffect: Effect) {
        viewModelScope.launch {
            _uiEffect.send(uiEffect)
        }
    }

    protected fun sendUiError(uiError: Error) {
        viewModelScope.launch {
            _uiError.send(uiError)
        }
    }

    protected fun updateState(reducer: (State) -> State) {
        _state.update { currentState ->
            val nextState = reducer(currentState)
            val isNewStateChanged= nextState != currentState
            // Only emit if the state actually changed to avoid extra render calls
            if (isNewStateChanged) nextState else currentState
        }
    }
}