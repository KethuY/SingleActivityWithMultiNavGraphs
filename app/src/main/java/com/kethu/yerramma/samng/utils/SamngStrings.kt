package com.kethu.yerramma.samng.utils

import androidx.annotation.StringRes
import com.kethu.yerramma.samng.R

/**
 * @Author: Yerramma Kethu
 * @Date: 10/01/2026
 */
enum class SamngStrings(@StringRes val resourceValue: Int) : SamngString {
    CONNECT(R.string.dash_connect),
    FOLLOW_US(R.string.menu_follow_us),
    THEME(R.string.menu_theme_appearance),
    PROFILE(R.string.menu_profile),
    PREMIUM(R.string.menu_premium),
    CHAT(R.string.menu_chat),
    COMMUNITY(R.string.menu_community),
    SETTINGS(R.string.menu_settings),
    BOOKMARKS(R.string.menu_bookmarks),
    SPACES(R.string.menu_spaces),
    TODOS(R.string.menu_todos),
    CARD_DETAILS(R.string.dash_card_details);

    override fun getString() = getStringFromRes(resourceValue)
}

interface SamngString {
    fun getString(): String
}

enum class SamngIcons(val resource: Int) {
    IC_LOG(R.drawable.ic_log),
    IC_THEME(R.drawable.ic_theme)
}