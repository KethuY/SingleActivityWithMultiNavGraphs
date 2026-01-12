package com.kethu.yerramma.samng.uikit.components.widgets.widgets.cards

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.kethu.yerramma.samng.uikit.components.atoms.CustomImage
import com.kethu.yerramma.samng.uikit.components.atoms.CustomText
import com.kethu.yerramma.samng.uikit.components.atoms.uidatamodels.ImageUiDataModel
import com.kethu.yerramma.samng.uikit.components.atoms.uidatamodels.TextUiDataModel
import com.kethu.yerramma.samng.uikit.components.molecules.footer.VerticaItems
import com.kethu.yerramma.samng.uikit.components.widgets.widgets.cards.properties.DetailCardUiModel
import com.kethu.yerramma.samng.uikit.ui.theme.Style14CaptionRegular

/**
 * @Author: Yerramma Kethu
 * @Date: 21/12/2025
 */
@Composable
fun UiDetailCard(
    properties: DetailCardUiModel,
    modifier: Modifier = Modifier,
    onCommentClicked: (String) -> Unit = {},
    onLikeClicked: (String) -> Unit = {},
    onItemClicked: (DetailCardUiModel) -> Unit,
) {
    with(properties) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = modifier,
            colors = CardDefaults.cardColors(containerColor = backgroundColor)
        ) {
            Column(modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    CustomImage(
                        modifier = Modifier.size(40.dp),
                        properties = ImageUiDataModel(src = profileUrl)
                    )
                    Spacer(Modifier.width(8.dp))
                    CustomText(
                        properties = TextUiDataModel(
                            text = profileName,
                            textStyle = Style14CaptionRegular
                        )
                    )
                    Spacer(Modifier.weight(1f))
                    CustomImage(
                        modifier = Modifier.size(24.dp),
                        properties = ImageUiDataModel(src = trailingIcon)
                    )
                }
                if (!imageUrl.isNullOrBlank()) {
                    Spacer(Modifier.height(8.dp))
                    Box(modifier = Modifier.pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                onItemClicked.invoke(properties)
                            }
                        )
                    }) {
                        CustomImage(
                            modifier = Modifier.fillMaxWidth(),
                            properties = ImageUiDataModel(
                                src = imageUrl,
                                contentScale = ContentScale.FillBounds
                            )
                        )
                        if (shareIcons.isNotEmpty()) {
                            VerticaItems(
                                modifier = Modifier
                                    .padding(horizontal = 12.dp, vertical = 12.dp)
                                    .align(Alignment.BottomEnd), shareIcons
                            )
                        }
                    }
                }
                if (!description.isBlank()) {
                    Spacer(Modifier.height(8.dp))
                    CustomText(
                        properties = TextUiDataModel(
                            text = description,
                            textStyle = Style14CaptionRegular
                        )
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .pointerInput(Unit) {
                                detectTapGestures(onTap = {
                                    onCommentClicked.invoke(id)
                                })
                            }
                            .fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CustomText(
                            properties = TextUiDataModel(
                                text = "Comments $commentCnt",
                                textStyle = Style14CaptionRegular
                            )
                        )
                    }

                    Row(
                        modifier = Modifier
                            .pointerInput(Unit) {
                                detectTapGestures(onTap = {
                                    onLikeClicked.invoke(id)
                                })
                            }
                            .fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CustomText(
                            properties = TextUiDataModel(
                                text = "Likes $likeCnt",
                                textStyle = Style14CaptionRegular
                            )
                        )
                    }
                }
            }
        }
    }
}