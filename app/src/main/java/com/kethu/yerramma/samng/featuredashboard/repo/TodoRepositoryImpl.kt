package com.kethu.yerramma.samng.featuredashboard.repo

import com.google.gson.reflect.TypeToken
import com.kethu.yerramma.samng.featuredashboard.repo.DashboardApiPaths.TODOS
import com.kethu.yerramma.samng.featuredashboard.repo.response.Todo
import com.kethu.yerramma.samng.networkmodule.client.ApiRequestBuilder
import com.kethu.yerramma.samng.networkmodule.client.NetworkDataSource
import com.kethu.yerramma.samng.networkmodule.client.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @Author: Yerramma Kethu
 * @Date: 22/12/2025
 */
class TodoRepositoryImpl @Inject constructor(private val dataSource: NetworkDataSource) :
    TodoRepository {

    override fun getTodos(): Flow<Resource<List<Todo>?>> = dataSource.send(
        ApiRequestBuilder.createBasicGetRequest(
            TODOS,
            object : TypeToken<List<Todo>>() {}.type
        )
    )
}