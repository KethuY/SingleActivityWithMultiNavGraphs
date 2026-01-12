package com.kethu.yerramma.samng.featuredashboard.uidatamodels

import com.kethu.yerramma.samng.utils.SamngIcons

/**
 * @Author: Yerramma Kethu
 */

data class DrawerMenuDataItem(
    val title: String,
    val iconResId: Any = SamngIcons.IC_LOG.resource,
    val type: Any
)

