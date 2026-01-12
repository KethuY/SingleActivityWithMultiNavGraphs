package com.kethu.yerramma.samng.featuredashboard.repo

import com.kethu.yerramma.samng.featuredashboard.repo.response.Todo
import com.kethu.yerramma.samng.networkmodule.client.Resource
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getTodos(): Flow<Resource<List<Todo>?>>
}