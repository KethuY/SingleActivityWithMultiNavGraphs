package com.kethu.yerramma.samng.featuredashboard.uidatamodels

import com.kethu.yerramma.samng.featuredashboard.uimodels.TodoUiModel

/**
 * @Author: Yerramma Kethu
 * @Date: 27/12/2025
 */
data class TodoUiDataModel(
    override val id: Int,
    override val title: String,
    override val isCompleted: Boolean,
    override val userId: Int
): TodoUiModel