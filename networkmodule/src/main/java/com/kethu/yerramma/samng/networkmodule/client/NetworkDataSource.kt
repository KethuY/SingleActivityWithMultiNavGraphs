package com.kethu.yerramma.samng.networkmodule.client

import kotlinx.coroutines.flow.Flow

interface NetworkDataSource {
    fun <T> send(request: BaseRequest): Flow<Resource<T>>
    fun <T> sendMultiPartRequest(request: BaseRequest): Flow<Resource<T>>
}