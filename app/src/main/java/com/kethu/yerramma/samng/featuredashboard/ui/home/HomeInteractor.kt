package com.kethu.yerramma.samng.featuredashboard.ui.home

import androidx.compose.ui.graphics.Color
import com.kethu.yerramma.samng.R
import com.kethu.yerramma.samng.featuredashboard.repo.DashboardConstants.HOME_ITEMS_SIZE
import com.kethu.yerramma.samng.uikit.components.widgets.widgets.cards.uidatamodels.DetailCardUiDataModel
import javax.inject.Inject

/**
 * @Author: Yerramma Kethu
 */

class HomeInteractor @Inject constructor() {

    // should be replaced with real data fetching logic from server
    fun getHomeData(): List<DetailCardUiDataModel> = List(HOME_ITEMS_SIZE) { index ->
        DetailCardUiDataModel(
            id = index.toString(),
            profileUrl = "https://data.sandbox.directory.openfinance.ae/logos/73423662-b345-453e-a54b-2f9115a6a45d/softwarestatements/1a703edb-b138-4fae-99aa-8348d418f296.png",
            shareIcons = listOf(R.drawable.ic_noti, R.drawable.ic_log, R.drawable.ic_search),
            description = "Venky and his friends quickly escape from there. On a belief that the police academy is the only safe haven for them",
            trailingIcon = R.drawable.ic_dots,
            profileName = "Title $index",
            imageUrl = "https://dummyimage.com/600x500/b06db0/b06db0",
            backgroundColor = Color.White,
            comments = "Comments",
            commentCnt = 20,
            likes = "Likes",
            likeCnt = 200,
        )
    }
}