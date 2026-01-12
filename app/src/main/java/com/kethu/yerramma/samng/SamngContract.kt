package com.kethu.yerramma.samng

import com.kethu.yerramma.samng.base.BaseUiEffect
import com.kethu.yerramma.samng.base.BaseUiState

sealed class SamngEvent {
    data object GetUserStatus : SamngEvent()
    data object ClearLoginPreferences : SamngEvent()
}

data object SamngUiState : BaseUiState

sealed interface SamngUiEffect : BaseUiEffect {
    data class LaunchAuthOrDashboardScreen(
        val isNewUser: Boolean,
        val isSignInUser: Boolean
    ) : SamngUiEffect
}
