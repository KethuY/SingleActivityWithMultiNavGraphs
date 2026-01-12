package com.kethu.yerramma.samng.featuredashboard.ui.home

import com.kethu.yerramma.samng.base.BaseUiEffect
import com.kethu.yerramma.samng.base.BaseUiError
import com.kethu.yerramma.samng.base.ComposeBaseViewModel
import com.kethu.yerramma.samng.uikit.components.widgets.widgets.cards.properties.DetailCardUiModel
import com.kethu.yerramma.samng.uikit.components.widgets.widgets.cards.uidatamodels.DetailCardUiDataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Author: Yerramma Kethu
 */
@HiltViewModel
class HomeViewModel @Inject constructor(private val interactor: HomeInteractor) :
    ComposeBaseViewModel<HomeEvent, HomeUiState, BaseUiEffect, BaseUiError>() {

    override fun onAction(action: HomeEvent) {
        when (action) {
            is HomeEvent.OnHomeItemClicked -> onHomeItemClicked(action.cardUiModel)
            is HomeEvent.GetHomeItems -> sendUiState(state.value.copy(items = interactor.getHomeData()))
            is HomeEvent.OnCommentClicked -> onCommentClicked(action.id)
            is HomeEvent.OnLikeClicked -> onLikedClicked(action.id)
        }
    }

    private fun onLikedClicked(id: String) {
        updateState { currentState ->
            val updatedList = currentState.items.map { item ->
                if (item.id == id && item is DetailCardUiDataModel) {
                    item.copy(likeCnt = item.likeCnt + 1)
                } else {
                    item
                }
            }
            currentState.copy(items = updatedList)
        }
    }

    private fun onCommentClicked(id: String) {
        updateState { currentState ->
            val updatedList = currentState.items.map { item ->
                if (item.id == id && item is DetailCardUiDataModel) {
                    item.copy(commentCnt = item.commentCnt + 1)
                } else {
                    item
                }
            }
            currentState.copy(items = updatedList)
        }
    }

    private fun onHomeItemClicked(cardUiModel: DetailCardUiModel) {

    }

    override fun defaultState(): HomeUiState = HomeUiState()

}