package com.kethu.yerramma.samng.featuredashboard.ui.todos

import com.kethu.yerramma.samng.featuredashboard.uidatamodels.TodoUiDataModel
import com.kethu.yerramma.samng.featuredashboard.repo.TodoRepository
import com.kethu.yerramma.samng.networkmodule.client.NetworkState
import com.kethu.yerramma.samng.networkmodule.client.Resource
import com.kethu.yerramma.samng.networkmodule.client.TransformResponse
import com.kethu.yerramma.samng.utils.ZERO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * @Author: Yerramma Kethu
 * @Date: 22/12/2025
 */
class TodosInteractor @Inject constructor(private val repository: TodoRepository) {
    fun fetchTodos(): Flow<TransformResponse> = repository.getTodos().map { networkResponse ->
        networkResponse.data?.let { todoItems ->
            val todos = todoItems.map { todo ->
                TodoUiDataModel(
                    id = todo.id ?: ZERO,
                    title = todo.title.orEmpty(),
                    isCompleted = todo.completed ?: false,
                    userId = todo.userId ?: ZERO
                )
            }
            Resource.Success(todos, NetworkState.SUCCESS)
        } ?: networkResponse
    }
}