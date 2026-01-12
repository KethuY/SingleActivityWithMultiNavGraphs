package com.kethu.yerramma.samng.uikit.components.molecules.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.kethu.yerramma.samng.uikit.components.atoms.CustomText
import com.kethu.yerramma.samng.uikit.ui.theme.ColorSegmentAccent
import com.kethu.yerramma.samng.uikit.ui.theme.ColorTextSubdued
import com.kethu.yerramma.samng.uikit.ui.theme.Style14CaptionMedium
import com.kethu.yerramma.samng.uikit.ui.theme.Style16BodyRegular
import com.kethu.yerramma.samng.uikit.ui.theme.Style20H4Medium
import com.kethu.yerramma.samng.uikit.components.atoms.uidatamodels.TextUiDataModel

@Composable
fun UiAlertDialog(
    title: String? = null,
    message: String,
    confirmText: String? = null,
    dismissText: String? = null,
    onDismiss: () -> Unit = {},
    onConfirmButtonClick: (() -> Unit) = {}
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            if (!title.isNullOrBlank())
                CustomText(properties = TextUiDataModel(title, textStyle = Style20H4Medium))
        },
        text = {
            if (message.isNotBlank()) {
                CustomText(properties = TextUiDataModel(message, textStyle = Style16BodyRegular.copy(color = ColorTextSubdued)))
            }
        },
        confirmButton = {
            if (!confirmText.isNullOrBlank()) {
                TextButton(onClick = onConfirmButtonClick) {
                    CustomText(properties = TextUiDataModel(confirmText, textStyle = Style14CaptionMedium.copy(color = ColorSegmentAccent)))
                }
            }
        },
        dismissButton = {
            if (!dismissText.isNullOrBlank()) {
                TextButton(onClick = onDismiss) {
                    CustomText(properties = TextUiDataModel(dismissText, textStyle = Style14CaptionMedium.copy(color = ColorSegmentAccent)))
                }
            }
        }
    )
}
