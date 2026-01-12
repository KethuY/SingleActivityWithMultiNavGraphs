package com.kethu.yerramma.samng.featuredashboard.interactors

import com.kethu.yerramma.samng.featuredashboard.Bookmarks
import com.kethu.yerramma.samng.featuredashboard.Chat
import com.kethu.yerramma.samng.featuredashboard.Community
import com.kethu.yerramma.samng.featuredashboard.FollowUs
import com.kethu.yerramma.samng.featuredashboard.Premium
import com.kethu.yerramma.samng.featuredashboard.Profile
import com.kethu.yerramma.samng.featuredashboard.Settings
import com.kethu.yerramma.samng.featuredashboard.Spaces
import com.kethu.yerramma.samng.featuredashboard.THEME
import com.kethu.yerramma.samng.featuredashboard.Todos
import com.kethu.yerramma.samng.featuredashboard.uidatamodels.DrawerMenuDataItem
import com.kethu.yerramma.samng.featuretheme.ThemeType
import com.kethu.yerramma.samng.utils.SamngIcons
import com.kethu.yerramma.samng.utils.SamngStrings
import javax.inject.Inject

/**
 * @Author: Yerramma Kethu
 * @Date: 10/01/2026
 */
class DashboardInteractor @Inject constructor() {

    fun getDrawerMenuItems() =
        listOf(
            DrawerMenuDataItem(
                title = SamngStrings.PROFILE.getString(),
                type = Profile
            ),
            DrawerMenuDataItem(
                title = SamngStrings.PREMIUM.getString(),
                type = Premium
            ),
            DrawerMenuDataItem(
                title = SamngStrings.CHAT.getString(),
                type = Chat
            ),
            DrawerMenuDataItem(
                title = SamngStrings.COMMUNITY.getString(),
                type = Community
            ),
            DrawerMenuDataItem(
                title = SamngStrings.SETTINGS.getString(),
                type = Settings
            ),
            DrawerMenuDataItem(
                title = SamngStrings.BOOKMARKS.getString(),
                type = Bookmarks
            ),
            DrawerMenuDataItem(
                title = SamngStrings.SPACES.getString(),
                type = Spaces
            ),
            DrawerMenuDataItem(
                title = SamngStrings.TODOS.getString(),
                type = Todos
            ),
            DrawerMenuDataItem(
                title = SamngStrings.THEME.getString(),
                iconResId = SamngIcons.IC_THEME.resource,
                type = THEME
            )
        )

    fun getProfileMenuItem() = DrawerMenuDataItem(
        title = "Jhon Do",
        iconResId = "https://data.sandbox.directory.openfinance.ae/logos/73423662-b345-453e-a54b-2f9115a6a45d/softwarestatements/1a703edb-b138-4fae-99aa-8348d418f296.png",
        type = Profile
    )

    fun getFollowUsMenuItem() = DrawerMenuDataItem(
        title = SamngStrings.FOLLOW_US.getString(),
        iconResId = SamngIcons.IC_LOG.resource,
        type = FollowUs
    )

    fun getAvailableThemes(): List<String> = ThemeType.entries.map { it.type }
}

