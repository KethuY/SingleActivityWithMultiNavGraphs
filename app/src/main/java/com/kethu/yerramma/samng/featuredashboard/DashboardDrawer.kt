package com.kethu.yerramma.samng.featuredashboard

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.kethu.yerramma.samng.R
import com.kethu.yerramma.samng.featuredashboard.uidatamodels.DrawerMenuDataItem
import com.kethu.yerramma.samng.uikit.components.atoms.CustomImage
import com.kethu.yerramma.samng.uikit.components.atoms.CustomText
import com.kethu.yerramma.samng.uikit.components.atoms.uidatamodels.ImageUiDataModel
import com.kethu.yerramma.samng.uikit.components.atoms.uidatamodels.TextUiDataModel
import com.kethu.yerramma.samng.uikit.ui.theme.Dimens.WIDTH_250
import com.kethu.yerramma.samng.uikit.ui.theme.LocalAppUiTheme
import com.kethu.yerramma.samng.uikit.ui.theme.Style14CaptionRegular
import com.kethu.yerramma.samng.uikit.ui.theme.Style16BodyRegular

@Composable
internal fun SamngDrawer(
    uiState: DashboardUiState,
    onClick: (Any, String) -> Unit,
    onCloseClicked: () -> Unit,
    content: @Composable () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    LaunchedEffect(uiState.isDrawerOpen) {
        if (uiState.isDrawerOpen) drawerState.open() else drawerState.close()
    }
    ModalNavigationDrawer(
        drawerContent = {
            DrawerContent(
                uiState = uiState,
                onClick = { type, itemTitle ->
                    onClick.invoke(type, itemTitle)
                },
                onCloseClicked = onCloseClicked
            )
        },
        drawerState = drawerState,
        content = content
    )
}

@Composable
private fun DrawerContent(
    uiState: DashboardUiState,
    onClick: (Any, String) -> Unit,
    onCloseClicked: () -> Unit
) {
    ModalDrawerSheet(drawerContainerColor = LocalAppUiTheme.current.backgroundColor) {
        Column(
            modifier = Modifier
                .width(WIDTH_250.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(24.dp))
            uiState.profileData?.let { profile ->
                ProfileIconSection(profile, onProfileIconClick = {
                    onClick.invoke(profile.title, profile.title)
                }, onCloseClicked = onCloseClicked)
                Spacer(Modifier.height(16.dp))
            }
            uiState.followUs?.let { followUs ->
                FollowUsSection(followUs) {
                    onClick.invoke(followUs.type, followUs.title)
                }
                Spacer(Modifier.height(16.dp))
            }
            uiState.menuItems?.let { menuDataItems ->
                DrawerMenuItems(menuDataItems) { type, itemTitle ->
                    onClick.invoke(type, itemTitle)
                }
            }
        }
    }
}

@Composable
private fun DrawerMenuItems(
    menuDataItems: List<DrawerMenuDataItem>,
    onClick: (Any, String) -> Unit
) {
    menuDataItems.forEach { item ->
        NavigationDrawerItem(
            icon = {
                CustomImage(
                    modifier = Modifier.size(24.dp),
                    properties = ImageUiDataModel(
                        src = item.iconResId,
                        colorFilter = ColorFilter.tint(LocalAppUiTheme.current.textColor)
                    )
                )
            },
            label = {
                CustomText(
                    properties = TextUiDataModel(
                        text = item.title,
                        textStyle = Style16BodyRegular.copy(color = LocalAppUiTheme.current.textColor)
                    )
                )
            },
            selected = false,
            onClick = {
                onClick.invoke(item.type, item.title)
            }
        )
    }
}

@Composable
private fun ProfileIconSection(
    profile: DrawerMenuDataItem,
    onProfileIconClick: () -> Unit,
    onCloseClicked: () -> Unit
) {
    Row(
        modifier = Modifier.plainClick(onProfileIconClick),
        horizontalArrangement = Arrangement.Center
    ) {
        CustomImage(
            modifier = Modifier
                .plainClick(onCloseClicked)
                .padding(start = 16.dp)
                .size(16.dp),
            properties = ImageUiDataModel(
                src = R.drawable.x,
                colorFilter = ColorFilter.tint(Color.White)
            )
        )
        Spacer(Modifier.width(48.dp))
        Column(modifier = Modifier.plainClick(onProfileIconClick)) {
            CustomImage(
                modifier = Modifier
                    .clip(RoundedCornerShape(30.dp))
                    .size(60.dp)
                    .border(3.dp, Color.White, shape = RoundedCornerShape(30.dp)),
                properties = ImageUiDataModel(src = profile.iconResId)
            )
            Spacer(Modifier.height(8.dp))
            CustomText(
                properties = TextUiDataModel(
                    text = profile.title,
                    textStyle = Style14CaptionRegular.copy(Color.White)
                )
            )
        }
    }
}

@Composable
private fun FollowUsSection(followUs: DrawerMenuDataItem, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(start = 32.dp)
            .plainClick(onClick)
    ) {
        CustomImage(
            modifier = Modifier
                .size(14.dp),
            properties = ImageUiDataModel(
                src = followUs.iconResId,
                colorFilter = ColorFilter.tint(Color.White)
            )
        )
        CustomText(
            properties = TextUiDataModel(
                text = followUs.title,
                textStyle = Style14CaptionRegular.copy(Color.White)
            )
        )
    }
}

internal fun Modifier.plainClick(onClick: () -> Unit): Modifier = this.pointerInput(onClick) {
    detectTapGestures(onTap = {
        onClick.invoke()
    })
}