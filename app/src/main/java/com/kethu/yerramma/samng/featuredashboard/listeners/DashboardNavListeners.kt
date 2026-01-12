package com.kethu.yerramma.samng.featuredashboard.listeners


/**
 * @Author: Yerramma Kethu
 * @Date: 23/12/2025
 */
interface DashboardNavListeners {
    val onDestinationChange: (Any) -> Unit
    val onBackClick: () -> Unit
    val navigateToAuthScreen: () -> Unit
}