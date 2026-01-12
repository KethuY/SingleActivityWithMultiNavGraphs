package com.kethu.yerramma.samng

import androidx.lifecycle.viewModelScope
import com.kethu.yerramma.samng.base.BaseUiError
import com.kethu.yerramma.samng.base.BaseUiState
import com.kethu.yerramma.samng.base.ComposeBaseViewModel
import com.kethu.yerramma.samng.datastore.DataStoreConstants
import com.kethu.yerramma.samng.datastore.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Author: Yerramma Kethu
 * @Date: 19/12/2025
 */
@HiltViewModel
class SamngViewModel @Inject constructor(private val dateStoreRepository: DataStoreRepository) :
    ComposeBaseViewModel<SamngEvent, BaseUiState, SamngUiEffect, BaseUiError>() {

    override fun defaultState() = SamngUiState

    override fun onAction(action: SamngEvent) {
        when (action) {
            is SamngEvent.ClearLoginPreferences -> clearPreferences()
            is SamngEvent.GetUserStatus -> handleUserNavigation()
        }
    }

    private fun clearPreferences() {
        viewModelScope.launch {
            dateStoreRepository.putPreference(DataStoreConstants.IS_USER_SIGNED_IN, false)
        }
    }

    private fun handleUserNavigation() {
        viewModelScope.launch {
            val isSignInUser = dateStoreRepository.getPreference(DataStoreConstants.IS_USER_SIGNED_IN, false)
            val isNewUser = dateStoreRepository.getPreference(DataStoreConstants.IS_NEW_USER, true)
            sendUiEffect(SamngUiEffect.LaunchAuthOrDashboardScreen(isNewUser, isSignInUser))
        }
    }
}