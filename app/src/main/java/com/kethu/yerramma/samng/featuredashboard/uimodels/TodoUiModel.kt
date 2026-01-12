package com.kethu.yerramma.samng.featuredashboard.uimodels

import com.kethu.yerramma.samng.base.BaseItem


/**
 * @Author: Yerramma Kethu
 * @Date: 27/12/2025
 */
interface TodoUiModel : BaseItem {
    val id: Int
    val title: String
    val isCompleted: Boolean
    val userId: Int
}