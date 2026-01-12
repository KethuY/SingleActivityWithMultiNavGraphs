package com.kethu.yerramma.samng.featuretheme

import androidx.lifecycle.viewModelScope
import com.kethu.yerramma.samng.base.BaseUiEffect
import com.kethu.yerramma.samng.base.BaseUiError
import com.kethu.yerramma.samng.base.ComposeBaseViewModel
import com.kethu.yerramma.samng.featuretheme.interactors.ThemeInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(private val interactor: ThemeInteractor) :
    ComposeBaseViewModel<ThemeEvent, ThemeUiState, BaseUiEffect, BaseUiError>() {

    override fun onAction(action: ThemeEvent) {
        when (action) {
            is ThemeEvent.GetCurrentTheme -> handleThemeChange()
            is ThemeEvent.SetSelectedTheme -> setSelectedTheme(action.selectedTheme)
        }
    }

    private fun setSelectedTheme(selectedTheme: String) {
        viewModelScope.launch {
            interactor.run {
                saveSelectedThemeType(selectedTheme)
                val updatedTheme = getSelectedThemeUiModel()
                sendUiState(state.value.copy(theme = updatedTheme, selectedThemeType =selectedTheme ))
            }
        }
    }

    private fun handleThemeChange() {
        viewModelScope.launch {
            interactor.run {
                val theme = getSelectedThemeUiModel()
                sendUiState(state.value.copy(theme = theme, selectedThemeType = getSelectedThemeType()))
            }
        }
    }

    override fun defaultState(): ThemeUiState = ThemeUiState()
}