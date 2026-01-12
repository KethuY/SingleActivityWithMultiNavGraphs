package com.kethu.yerramma.samng.navgraphs

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.kethu.yerramma.samng.R
import com.kethu.yerramma.samng.SamngActivity
import com.kethu.yerramma.samng.featuredashboard.Bookmarks
import com.kethu.yerramma.samng.featuredashboard.Card
import com.kethu.yerramma.samng.featuredashboard.Chat
import com.kethu.yerramma.samng.featuredashboard.Community
import com.kethu.yerramma.samng.featuredashboard.Dashboard
import com.kethu.yerramma.samng.featuredashboard.DashboardEvent
import com.kethu.yerramma.samng.featuredashboard.DashboardGraph
import com.kethu.yerramma.samng.featuredashboard.DashboardScreen
import com.kethu.yerramma.samng.featuredashboard.DashboardViewModel
import com.kethu.yerramma.samng.featuredashboard.FollowUs
import com.kethu.yerramma.samng.featuredashboard.HomePage
import com.kethu.yerramma.samng.featuredashboard.Premium
import com.kethu.yerramma.samng.featuredashboard.Profile
import com.kethu.yerramma.samng.featuredashboard.Settings
import com.kethu.yerramma.samng.featuredashboard.Spaces
import com.kethu.yerramma.samng.featuredashboard.Todos
import com.kethu.yerramma.samng.featuredashboard.listeners.DashboardNavListenersImpl
import com.kethu.yerramma.samng.featuredashboard.ui.ComingSoonScreen
import com.kethu.yerramma.samng.featuredashboard.ui.home.HomeScreen
import com.kethu.yerramma.samng.featuredashboard.ui.home.HomeViewModel
import com.kethu.yerramma.samng.featuredashboard.ui.todos.TodosScreen
import com.kethu.yerramma.samng.featuretheme.ThemeViewModel
import com.kethu.yerramma.samng.utils.getStringFromRes

/**
 * @Author: Yerramma Kethu
 * @Date: 05/01/2026
 */
fun NavGraphBuilder.dashboardRootGraph(
    rootNavController: NavController,
    themeViewModel: ThemeViewModel
) {
    navigation<DashboardGraph>(startDestination = Dashboard) {
        composable<Dashboard> {
            val viewModel: DashboardViewModel = hiltViewModel<DashboardViewModel>()
            val dashboardNavController = rememberNavController()
            val navBackStackEntry by dashboardNavController.currentBackStackEntryAsState()
            val context = LocalContext.current
            BackHandler {
                viewModel.onAction(DashboardEvent.LogoutUser)
                // Correctly closes the current Activity
                (context as? Activity)?.finishAndRemoveTask()
            }

            DashboardScreen(
                viewModel = viewModel,
                themeViewModel = themeViewModel,
                backNavEntry = navBackStackEntry,
                onClickListeners = DashboardNavListenersImpl(
                    onDestinationChange = { destination ->
                        handleNavigation(dashboardNavController, destination)
                    },
                    onBackClick = {
                        dashboardNavController.navigateUp()
                    },
                    navigateToAuthScreen = {
                        val intent = Intent(context, SamngActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        }
                        context.startActivity(intent)
                    }
                )) { paddingValues ->

                NavHost(
                    navController = dashboardNavController,
                    startDestination = HomePage,
                    modifier = Modifier.padding(paddingValues),
                ) {
                    composable<HomePage> {
                        HomeScreen()
                    }
                    composable<Profile> {
                        ComingSoonScreen(getStringFromRes(R.string.menu_profile)) {
                            dashboardNavController.navigate(Card)
                        }
                    }
                    composable<Chat> {
                        ComingSoonScreen(getStringFromRes(R.string.menu_chat))
                    }

                    composable<Community> {
                        ComingSoonScreen(getStringFromRes(R.string.menu_community))
                    }
                    composable<Settings> {
                        ComingSoonScreen(getStringFromRes(R.string.menu_settings))
                    }

                    composable<Premium> {
                        ComingSoonScreen(getStringFromRes(R.string.menu_premium))
                    }

                    composable<Bookmarks> {
                        ComingSoonScreen(getStringFromRes(R.string.menu_bookmarks))
                    }

                    composable<Spaces> {
                        ComingSoonScreen(getStringFromRes(R.string.menu_spaces))
                    }

                    composable<FollowUs> {
                        ComingSoonScreen(getStringFromRes(R.string.menu_follow_us))
                    }

                    composable<Todos> {
                        TodosScreen()
                    }

                    composable<Card> {
                        ComingSoonScreen(getStringFromRes(R.string.dash_card_details))
                    }
                }
            }
        }
    }
}


private fun handleNavigation(navController: NavController, des: Any) {
    navController.navigate(des) {
        launchSingleTop = true
        restoreState = true
    }
}