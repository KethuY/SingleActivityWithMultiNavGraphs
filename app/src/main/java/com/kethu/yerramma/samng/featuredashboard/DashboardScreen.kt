package com.kethu.yerramma.samng.featuredashboard

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import com.kethu.yerramma.samng.R
import com.kethu.yerramma.samng.featuredashboard.listeners.DashboardNavListeners
import com.kethu.yerramma.samng.featuredashboard.uidatamodels.SamngToolbarUiDataModel
import com.kethu.yerramma.samng.featuretheme.ThemeEvent
import com.kethu.yerramma.samng.featuretheme.ThemeSelection
import com.kethu.yerramma.samng.featuretheme.ThemeViewModel
import com.kethu.yerramma.samng.uikit.components.atoms.CustomImage
import com.kethu.yerramma.samng.uikit.components.atoms.uidatamodels.ImageUiDataModel
import com.kethu.yerramma.samng.uikit.components.molecules.toolbar.UiToolbarCustomActions
import com.kethu.yerramma.samng.uikit.ui.theme.ColorBannersInfoBg
import com.kethu.yerramma.samng.utils.simpleRoute
import kotlinx.coroutines.launch

/**
 * @Author: Yerramma Kethu
 * @Date: 06/01/2026
 */
@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel,
    themeViewModel: ThemeViewModel,
    onClickListeners: DashboardNavListeners,
    backNavEntry: NavBackStackEntry?,
    content: @Composable (PaddingValues) -> Unit
) {
    val uiState = viewModel.state.collectAsStateWithLifecycle()
    val uiThemeState = themeViewModel.state.collectAsStateWithLifecycle()
    var showAuthScreen by rememberSaveable { mutableStateOf(false) }
    var themeTypes by rememberSaveable { mutableStateOf<List<String>?>(null) }
    LaunchedEffect(backNavEntry) {
        val route = backNavEntry?.destination?.simpleRoute.orEmpty()
        viewModel.onAction(DashboardEvent.OnRouteChanged(route))
    }
    LaunchedEffect(Unit) {
        viewModel.onAction(DashboardEvent.GetUiData)
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(key1 = lifecycleOwner) {
        launch {
            viewModel.uiEffect.collect { effect ->
                when (effect) {
                    is DashboardUiEffect.ShowThemBottomSheet -> {
                        themeTypes = effect.types
                    }

                    is DashboardUiEffect.NavigateToAuthScreen -> {
                        showAuthScreen = true
                    }

                    is DashboardUiEffect.NavigateToDestination -> {
                        onClickListeners.onDestinationChange.invoke(effect.type)
                    }

                    is DashboardUiEffect.NavigateToPreviousScreen -> {
                        onClickListeners.onBackClick.invoke()
                    }
                }
            }
        }
    }

    SamngDrawer(
        uiState = uiState.value,
        onClick = { type, itemTitle ->
            viewModel.onAction(DashboardEvent.OnDrawerMenuItemClicked(type, itemTitle))
        },
        onCloseClicked = {
            viewModel.onAction(DashboardEvent.OnCloseIconClick)
        },
    ) {
        Scaffold(
            modifier = Modifier.padding(horizontal = 16.dp),
            containerColor = ColorBannersInfoBg.copy(alpha = 0.2f),
            topBar = {
                SamngAppBar(
                    title = uiState.value.toolbarTitle,
                    showBack = uiState.value.showBackButton,
                    onSearchClick = {},
                    onLogoutClick = {
                        viewModel.onAction(DashboardEvent.LogoutUser)
                    },
                    onNotificationClick = {},
                    onNavClick = {
                        viewModel.onAction(DashboardEvent.OnNavIconClick)
                    })
            }
        ) { innerPadding ->
            content.invoke(innerPadding)
        }
    }
    if (!themeTypes.isNullOrEmpty()) {
        val selectedTheme = uiThemeState.value.selectedThemeType
        ThemeSelection(
            types = themeTypes.orEmpty(),
            selectedType = selectedTheme,
            onThemeSelected = { selTheme ->
                themeTypes = null
                themeViewModel.onAction(ThemeEvent.SetSelectedTheme(selTheme))
            },
            onDismissRequest = {
                themeTypes = null
            }
        )
    }
    if (showAuthScreen) {
        onClickListeners.navigateToAuthScreen.invoke()
    }
}

@Composable
private fun SamngAppBar(
    title: String,
    showBack: Boolean,
    onSearchClick: () -> Unit,
    onNotificationClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onNavClick: () -> Unit
) {
    UiToolbarCustomActions(
        properties = SamngToolbarUiDataModel(
            title = title,
            navigationIcon = if (showBack) R.drawable.ic_arrow_back else R.drawable.ic_menu
        ),
        onBackClick = onNavClick,
        actions = {
            if (!showBack)
                ToolBarMenuItems(onSearchClick, onNotificationClick, onLogoutClick)
        }
    )
}

@Composable
private fun ToolBarMenuItems(
    onSearchClick: () -> Unit,
    onNotificationClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    CustomImage(
        modifier = Modifier
            .padding(end = 16.dp)
            .plainClick(onSearchClick),
        properties = ImageUiDataModel(
            src = R.drawable.ic_search,
        )
    )

    CustomImage(
        modifier = Modifier
            .padding(end = 16.dp)
            .plainClick(onNotificationClick),
        properties = ImageUiDataModel(
            src = R.drawable.ic_noti,
        )
    )

    CustomImage(
        modifier = Modifier
            .padding(end = 16.dp)
            .plainClick(onLogoutClick),
        properties = ImageUiDataModel(
            src = R.drawable.ic_log,
        )
    )
}