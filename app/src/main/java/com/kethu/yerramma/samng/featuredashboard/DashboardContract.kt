package com.kethu.yerramma.samng.featuredashboard

import com.kethu.yerramma.samng.base.BaseUiEffect
import com.kethu.yerramma.samng.base.BaseUiState
import com.kethu.yerramma.samng.featuredashboard.uidatamodels.DrawerMenuDataItem
import com.kethu.yerramma.samng.utils.SamngStrings

/**
 * Created by Kethu on 06/07/2024.
 */
sealed class DashboardEvent {
    data object LogoutUser :
        DashboardEvent()

    data object GetUiData : DashboardEvent()
    data object OnCloseIconClick : DashboardEvent()
    data object OnNavIconClick : DashboardEvent()
    data class OnDrawerMenuItemClicked(val type: Any, val title: String) : DashboardEvent()
    data class OnRouteChanged(val route: String) : DashboardEvent()
    data object ClearLoginCredentials : DashboardEvent()
}

data class DashboardUiState(
    val isDrawerOpen: Boolean = false,
    val profileData: DrawerMenuDataItem? = null,
    val followUs: DrawerMenuDataItem? = null,
    val menuItems: List<DrawerMenuDataItem>? = null,
    val showBackButton: Boolean = false,
    val toolbarTitle: String = SamngStrings.CONNECT.getString()
) : BaseUiState

sealed interface DashboardUiEffect : BaseUiEffect {
    data class ShowThemBottomSheet(val types: List<String>) : DashboardUiEffect
    data object NavigateToPreviousScreen : DashboardUiEffect
    data object NavigateToAuthScreen : DashboardUiEffect
    data class NavigateToDestination(val type: Any) : DashboardUiEffect
}
