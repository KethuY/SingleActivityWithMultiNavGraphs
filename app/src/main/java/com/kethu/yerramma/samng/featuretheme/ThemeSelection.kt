package com.kethu.yerramma.samng.featuretheme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.kethu.yerramma.samng.R
import com.kethu.yerramma.samng.uikit.components.atoms.CustomText
import com.kethu.yerramma.samng.uikit.components.atoms.uidatamodels.TextUiDataModel
import com.kethu.yerramma.samng.uikit.components.layouts.UiModelBottomSheet
import com.kethu.yerramma.samng.uikit.ui.theme.Style16BodyRegular

@Composable
fun ThemeSelection(
    types: List<String>,
    selectedType: String,
    modifier: Modifier = Modifier,
    onThemeSelected: (String) -> Unit,
    onDismissRequest: () -> Unit = {}
) {
    var selectedOption by rememberSaveable { mutableStateOf(selectedType) }

    UiModelBottomSheet(
        title = stringResource(R.string.dash_select_them),
        closeIcon = R.drawable.ic_close,
        contentItem = {
            Column(modifier.selectableGroup()) {
                types.forEach { text ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .selectable(
                                selected = (text == selectedType),
                                onClick = {
                                    selectedOption = text
                                    onThemeSelected.invoke(text)
                                },
                                role = Role.RadioButton
                            )
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (text == selectedOption),
                            onClick = null
                        )
                        CustomText(
                            modifier = Modifier.padding(start = 16.dp),
                            properties = TextUiDataModel(
                                text = text,
                                textStyle = Style16BodyRegular
                            )
                        )
                    }
                }
            }
        },
        onDismissRequest = {
            onDismissRequest.invoke()
        })
}
