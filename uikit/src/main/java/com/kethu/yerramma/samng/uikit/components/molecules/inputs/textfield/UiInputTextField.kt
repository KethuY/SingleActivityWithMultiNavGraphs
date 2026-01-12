package com.kethu.yerramma.samng.uikit.components.molecules.inputs.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kethu.yerramma.samng.uikit.components.atoms.CustomImage
import com.kethu.yerramma.samng.uikit.components.atoms.uidatamodels.ImageUiDataModel
import com.kethu.yerramma.samng.uikit.components.molecules.inputs.textfield.properties.InputListeners
import com.kethu.yerramma.samng.uikit.components.molecules.inputs.textfield.properties.InputProperties
import com.kethu.yerramma.samng.uikit.utils.getImage
import com.kethu.yerramma.samng.uikit.utils.isValidImage
import com.kethu.yerramma.samng.uikit.ui.theme.Dimens.SizeFontWebCaption
import com.kethu.yerramma.samng.uikit.ui.theme.Dimens.SizeSpacingLarge

@Composable
fun UiInputTextField(
    properties: InputProperties,
    listeners: InputListeners,
    modifier: Modifier = Modifier
) {
    BaseInputTextField(
        modifier = modifier.fillMaxWidth(),
        textInputData = properties,
        prefix = {
            if (properties.prefix.isValidImage()) {
                CustomImage(
                    modifier = Modifier
                        .padding(end = SizeFontWebCaption.dp)
                        .size(SizeSpacingLarge.dp),
                    properties = ImageUiDataModel(src = properties.prefix.getImage())
                )
            }
        },
        listeners = listeners
    )
}