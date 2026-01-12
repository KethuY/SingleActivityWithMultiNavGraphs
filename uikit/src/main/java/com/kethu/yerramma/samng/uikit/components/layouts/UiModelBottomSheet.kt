package com.kethu.yerramma.samng.uikit.components.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kethu.yerramma.samng.uikit.components.atoms.CustomImage
import com.kethu.yerramma.samng.uikit.components.atoms.CustomText
import com.kethu.yerramma.samng.uikit.components.atoms.uidatamodels.ImageUiDataModel
import com.kethu.yerramma.samng.uikit.ui.theme.ColorBackground
import com.kethu.yerramma.samng.uikit.ui.theme.Dimens.SizeSpacingLarge
import com.kethu.yerramma.samng.uikit.ui.theme.Style28H2Medium
import com.kethu.yerramma.samng.uikit.components.atoms.uidatamodels.TextUiDataModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UiModelBottomSheet(
    modifier: Modifier = Modifier,
    title: String? = null,
    closeIcon: Int? = null,
    skipPartiallyExpanded: Boolean = false,
    isCancellable: Boolean = true,
    backgroundColor: Color = ColorBackground,
    contentItem: @Composable () -> Unit,
    onDismissRequest: () -> Unit = {},
    dragHandle: (@Composable () -> Unit)? = null,
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded,
        confirmValueChange = { state ->
            (state != SheetValue.Hidden) || isCancellable
        }
    )

    val properties = ModalBottomSheetProperties(shouldDismissOnBackPress = isCancellable)
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = backgroundColor,
        properties = properties,
        dragHandle = dragHandle
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .padding(horizontal = SizeSpacingLarge.dp)
        ) {
            if (dragHandle == null) Spacer(modifier = Modifier.padding(top = SizeSpacingLarge.dp))
            closeIcon?.let { icon ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = { onDismissRequest.invoke() }
                        ),
                    horizontalArrangement = Arrangement.End
                ) {
                    CustomImage(properties = ImageUiDataModel(src = icon))
                }
            }
            title?.let { titleText ->
                CustomText(
                    properties = TextUiDataModel(
                        text = titleText,
                        textStyle = Style28H2Medium,
                    )
                )
            }
            contentItem()
        }
    }
}