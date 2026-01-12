package com.kethu.yerramma.samng.featuredashboard.ui.todos

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kethu.yerramma.samng.R
import com.kethu.yerramma.samng.base.BaseUiError
import com.kethu.yerramma.samng.commonui.NetworkErrorDialog
import com.kethu.yerramma.samng.featuredashboard.uimodels.TodoUiModel
import com.kethu.yerramma.samng.networkmodule.client.ErrorResponse
import com.kethu.yerramma.samng.uikit.components.atoms.CustomImage
import com.kethu.yerramma.samng.uikit.components.atoms.CustomText
import com.kethu.yerramma.samng.uikit.components.atoms.uidatamodels.ImageUiDataModel
import com.kethu.yerramma.samng.uikit.components.atoms.uidatamodels.TextUiDataModel
import com.kethu.yerramma.samng.uikit.components.molecules.loaders.UiLoader
import com.kethu.yerramma.samng.uikit.ui.theme.Style16BodyMedium

/**
 * @Author: Yerramma Kethu
 * @Date: 01/01/2026
 */
@Composable
fun TodosScreen(viewModel: TodoViewModel = hiltViewModel<TodoViewModel>()) {
    var currentDialogError by rememberSaveable { mutableStateOf<ErrorResponse?>(null) }
    val uiState = viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.onAction(TodoEvent.GetTodoItems)
    }

    if (uiState.value.items.isNotEmpty()) {
        LazyColumn {
            items(
                uiState.value.items.size,
                key = { index -> uiState.value.items[index].id }) { index ->
                TodoItemCard(properties = uiState.value.items[index])
            }
        }
    }

    if (uiState.value.isLoading) {
        UiLoader()
    }
    LaunchedEffect(Unit) {
        viewModel.uiError.collect { uiError ->
            when (uiError) {
                is BaseUiError.ShowErrorDialog -> {
                    currentDialogError = uiError.error
                }

                else -> Unit
            }
        }
    }

    currentDialogError?.let { error ->
        NetworkErrorDialog(error, onConfirmButton = {
            currentDialogError = null
        })
    }
}

@Composable
private fun TodoItemCard(properties: TodoUiModel) {
    Row(modifier = Modifier.padding(vertical = 16.dp)) {
        CustomText(
            modifier = Modifier.weight(1f),
            properties = TextUiDataModel(
                text = properties.title,
                textStyle = Style16BodyMedium
            )
        )
        CustomImage(
            modifier = Modifier.size(24.dp),
            properties = ImageUiDataModel(src = R.drawable.ic_forward_arrow)
        )
    }
}