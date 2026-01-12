package com.kethu.yerramma.samng.featuredashboard.ui.home

import com.kethu.yerramma.samng.base.BaseUiState
import com.kethu.yerramma.samng.uikit.components.widgets.widgets.cards.properties.DetailCardUiModel


sealed class HomeEvent {
    data object GetHomeItems : HomeEvent()
    data class OnCommentClicked(val id: String) : HomeEvent()
    data class OnLikeClicked(val id: String) : HomeEvent()
    data class OnHomeItemClicked(val cardUiModel: DetailCardUiModel) : HomeEvent()
}

data class HomeUiState(
    val items: List<DetailCardUiModel> = emptyList()
) : BaseUiState