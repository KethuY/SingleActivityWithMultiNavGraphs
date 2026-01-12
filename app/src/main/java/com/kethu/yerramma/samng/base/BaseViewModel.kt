package com.kethu.yerramma.samng.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler

abstract class BaseViewModel : ViewModel() {
    open val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ -> }
}