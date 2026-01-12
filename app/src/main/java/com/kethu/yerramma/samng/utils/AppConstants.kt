package com.kethu.yerramma.samng.utils

import com.kethu.yerramma.samng.R
import com.kethu.yerramma.samng.featureauth.uidatamodels.SocialMediaData


/**
 * @Author: Yerramma Kethu
 * @Date: 20/12/2025
 */
const val ZERO = 0
const val DEFAULT_EMPTY_STRING = ""
const val DEFAULT_DOT_STRING = "."
const val DEFAULT_QUESTION_STRING = "?"

fun getSocialMediaInfo() = listOf(
    SocialMediaData(R.drawable.fb, "https://www.facebook.com/"),
    SocialMediaData(R.drawable.insta, "https://www.instagram.com/"),
    SocialMediaData(R.drawable.x, "https://x.com/"),
    SocialMediaData(R.drawable.lin, "https://www.linkedin.com/")
)

