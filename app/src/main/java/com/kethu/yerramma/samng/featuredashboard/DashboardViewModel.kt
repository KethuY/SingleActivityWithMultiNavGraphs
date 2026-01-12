package com.kethu.yerramma.samng.featuredashboard

import androidx.lifecycle.viewModelScope
import com.kethu.yerramma.samng.base.BaseUiError
import com.kethu.yerramma.samng.base.ComposeBaseViewModel
import com.kethu.yerramma.samng.datastore.DataStoreConstants
import com.kethu.yerramma.samng.datastore.DataStoreRepository
import com.kethu.yerramma.samng.featuredashboard.interactors.DashboardInteractor
import com.kethu.yerramma.samng.utils.SamngStrings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Author: Yerramma Kethu
 * @Date: 20/12/2025
 */
@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val interactor: DashboardInteractor
) : ComposeBaseViewModel<DashboardEvent, DashboardUiState, DashboardUiEffect, BaseUiError>() {

    override fun defaultState(): DashboardUiState = DashboardUiState()

    override fun onAction(action: DashboardEvent) {
        when (action) {
            is DashboardEvent.LogoutUser -> logoutUser()
            is DashboardEvent.GetUiData -> manageUiData()
            is DashboardEvent.OnCloseIconClick -> handleDrawerState()
            is DashboardEvent.OnDrawerMenuItemClicked -> handleDrawerMenuItemClick(
                action.type,
                action.title
            )

            is DashboardEvent.OnNavIconClick -> onNavIconClick()
            is DashboardEvent.OnRouteChanged -> onRouteChanged(action.route)
           is DashboardEvent.ClearLoginCredentials -> clearLoginState()
        }
    }

    private fun clearLoginState() {
        viewModelScope.launch {
            dataStoreRepository.putPreference(
                DataStoreConstants.IS_USER_SIGNED_IN,
                false
            )
        }
    }

    private fun onRouteChanged(type: String) {
        val toolbarBarTitle = getScreenTitle(type)
        val showBackButton =
            !toolbarBarTitle.equals(SamngStrings.CONNECT.getString(), ignoreCase = true)
        sendUiState(
            state.value.copy(
                toolbarTitle = toolbarBarTitle,
                showBackButton = showBackButton,
                isDrawerOpen = false
            )
        )
    }

    private fun onNavIconClick() {
        if (state.value.showBackButton) {
            sendUiEffect(DashboardUiEffect.NavigateToPreviousScreen)
        } else {
            handleDrawerState()
        }
    }

    private fun handleDrawerMenuItemClick(type: Any, title: String) {
        if (THEME == type) {
            handleDrawerState()
            sendUiEffect(DashboardUiEffect.ShowThemBottomSheet(interactor.getAvailableThemes()))
        } else {
            sendUiState(
                state.value.copy(
                    toolbarTitle = title,
                    showBackButton = true,
                    isDrawerOpen = !state.value.isDrawerOpen
                )
            )
            sendUiEffect(DashboardUiEffect.NavigateToDestination(type))
        }
    }

    private fun handleDrawerState() {
        sendUiState(state.value.copy(isDrawerOpen = !state.value.isDrawerOpen))
    }

    private fun manageUiData() {
        interactor.run {
            sendUiState(
                state.value.copy(
                    profileData = getProfileMenuItem(),
                    followUs = getFollowUsMenuItem(),
                    menuItems = getDrawerMenuItems(),
                )
            )
        }
    }

    private fun logoutUser() {
        clearLoginState()
        sendUiEffect(DashboardUiEffect.NavigateToAuthScreen)
    }

    private fun getScreenTitle(type: String): String = when (type) {
        Profile::class.simpleName -> SamngStrings.PROFILE.getString()
        Settings::class.simpleName -> SamngStrings.SETTINGS.getString()
        Premium::class.simpleName -> SamngStrings.PREMIUM.getString()
        Chat::class.simpleName -> SamngStrings.CHAT.getString()
        Community::class.simpleName -> SamngStrings.COMMUNITY.getString()
        Bookmarks::class.simpleName -> SamngStrings.BOOKMARKS.getString()
        Spaces::class.simpleName -> SamngStrings.SPACES.getString()
        FollowUs::class.simpleName -> SamngStrings.TODOS.getString()
        Card::class.simpleName -> SamngStrings.CARD_DETAILS.getString()
        else -> SamngStrings.CONNECT.getString()
    }
}
