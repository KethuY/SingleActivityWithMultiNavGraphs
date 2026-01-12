package com.kethu.yerramma.samng.featuredashboard.listeners

/**
 * @Author: Yerramma Kethu
 * @Date: 23/12/2025
 */
class DashboardNavListenersImpl(
    override val onDestinationChange: (Any) -> Unit,
    override val onBackClick: () -> Unit,
    override val navigateToAuthScreen: () -> Unit
) : DashboardNavListeners