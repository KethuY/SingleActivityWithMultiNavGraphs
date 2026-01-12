package com.kethu.yerramma.samng.featuredashboard.uidatamodels

import com.kethu.yerramma.samng.R
import com.kethu.yerramma.samng.uikit.components.molecules.toolbar.properties.ToolbarProperties


data class SamngToolbarUiDataModel(
    override val title: String,
    override val navigationIcon: Int = R.drawable.ic_menu
) : ToolbarProperties