package com.kethu.yerramma.samng.base

import com.kethu.yerramma.samng.networkmodule.client.ErrorResponse

/**
 * @Author: Yerramma Kethu
 * @Date: 08/01/2026
 */

interface BaseUiEffect

interface BaseUiError  {
    data class ShowErrorDialog(val error: ErrorResponse?) : BaseUiError
    data class ShowToastMessage(val message: String) : BaseUiError
}

interface BaseUiState