package com.kethu.yerramma.samng.uikit.components.widgets.widgets.cards.properties

import com.kethu.yerramma.samng.uikit.components.BaseView


/**
 * @Author: Yerramma Kethu
 * @Date: 21/12/2025
 */
interface DetailCardUiModel : BaseView {
    val id: String
    val profileUrl: String
    val profileName: String
    val imageUrl: String?
    val description: String
    val trailingIcon: Int
    val comments: String?
    val commentCnt: Int
    val likes: String?
    val likeCnt: Int
    val shareIcons: List<Int>
}