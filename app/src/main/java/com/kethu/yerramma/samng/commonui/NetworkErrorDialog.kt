package com.kethu.yerramma.samng.commonui

import androidx.compose.runtime.Composable
import com.kethu.yerramma.samng.R
import com.kethu.yerramma.samng.networkmodule.client.ErrorConstants
import com.kethu.yerramma.samng.networkmodule.client.ErrorResponse
import com.kethu.yerramma.samng.uikit.components.molecules.dialog.UiAlertDialog
import com.kethu.yerramma.samng.utils.getStringFromRes
import kotlin.text.ifEmpty
import kotlin.text.orEmpty
import kotlin.to

/**
 * @Author: Yerramma Kethu
 * @Date: 31/12/2025
 */
@Composable
fun NetworkErrorDialog(
    errorResponse: ErrorResponse?,
    confirmText: String = getStringFromRes(R.string.ok),
    onConfirmButton: () -> Unit = {}
) {
    val (title, message) = getErrorTitle(errorResponse)
    UiAlertDialog(
        title = title,
        message = message.orEmpty(),
        confirmText = confirmText,
        onConfirmButtonClick = onConfirmButton
    )
}

@Composable
fun NetworkErrorDialog(
    errorResponse: ErrorResponse?,
    confirmText: String = getStringFromRes(R.string.ok),
    onConfirmButton: () -> Unit,
    dismissText: String,
    onDismissButton: () -> Unit
) {
    val (title, message) = getErrorTitle(errorResponse)
    UiAlertDialog(
        title = title.orEmpty(),
        message = message.orEmpty(),
        confirmText = confirmText,
        onConfirmButtonClick = onConfirmButton,
        dismissText = dismissText,
        onDismiss = onDismissButton
    )
}


private fun getErrorTitle(errorResponse: ErrorResponse?): Pair<String?, String?> {
    return when (errorResponse?.code) {
        ErrorConstants.NO_INTERNET ->
            getStringFromRes(R.string.no_internet_connection) to getStringFromRes(R.string.please_check_your_internet_connection)

        ErrorConstants.UNKNOWN_CODE ->
            getStringFromRes(R.string.error) to getStringFromRes(R.string.server_error_please_try_again_later)

        ErrorConstants.EXCEPTION_CODE ->
            getStringFromRes(R.string.error) to getStringFromRes(R.string.something_went_wrong_please_try_again_later)

        null -> defaultErrorMessage()
        else -> {
            val title = errorResponse.title.orEmpty()
                .ifEmpty { getStringFromRes(R.string.error) }
            val desc = errorResponse.desc.orEmpty()
            title to desc
        }
    }
}

private fun defaultErrorMessage() =
    getStringFromRes(R.string.error) to getStringFromRes(R.string.server_error_please_try_again_later)