package com.kethu.yerramma.samng.featuredashboard.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kethu.yerramma.samng.uikit.components.widgets.widgets.cards.UiDetailCard

/**
 * @Author: Yerramma Kethu
 * @Date: 21/12/2025
 */
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel= hiltViewModel()
) {
    val uiState = viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.onAction(HomeEvent.GetHomeItems)
    }
    if (uiState.value.items.isNotEmpty()) {
        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = uiState.value.items, key = { it.id }) { cardItem ->
                UiDetailCard(
                    properties = cardItem,
                    onLikeClicked = {
                        viewModel.onAction(HomeEvent.OnLikeClicked(it))
                    }, onCommentClicked = {
                        viewModel.onAction(HomeEvent.OnCommentClicked(it))
                    }, onItemClicked = {
                        viewModel.onAction(HomeEvent.OnHomeItemClicked(it))
                    })
            }
        }
    }
}