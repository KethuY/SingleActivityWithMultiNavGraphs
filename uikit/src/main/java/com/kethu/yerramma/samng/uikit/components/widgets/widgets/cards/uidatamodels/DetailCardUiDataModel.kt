package com.kethu.yerramma.samng.uikit.components.widgets.widgets.cards.uidatamodels

import androidx.compose.ui.graphics.Color
import com.kethu.yerramma.samng.uikit.components.widgets.widgets.cards.properties.DetailCardUiModel

/**
 * @Author: Yerramma Kethu
 * @Date: 21/12/2025
 */
data class DetailCardUiDataModel(
    override val backgroundColor: Color,
    override val profileUrl: String,
    override val profileName: String,
    override val imageUrl: String?,
    override val description: String,
    override val trailingIcon: Int,
    override val comments: String?,
    override val commentCnt: Int,
    override val likes: String?,
    override val likeCnt: Int,
    override val shareIcons: List<Int>,
    override val id: String
) : DetailCardUiModel