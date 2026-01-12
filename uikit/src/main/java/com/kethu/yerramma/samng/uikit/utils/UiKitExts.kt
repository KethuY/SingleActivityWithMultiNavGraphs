package com.kethu.yerramma.samng.uikit.utils

import android.graphics.Typeface
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.text.style.URLSpan
import android.text.style.UnderlineSpan
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.core.text.HtmlCompat
import com.kethu.yerramma.samng.uikit.ui.theme.ColorInteraction

/**
 * @Author: Yerramma Kethu
 * @Date: 07/10/2025
 * Converts an HTML-formatted string to an AnnotatedString for Jetpack Compose,
 * preserving styles such as bold, italic, underline, strikethrough, color, and links.
 * Use this extension to render HTML content from string resources.
 */
fun String.toAnnotatedString(): AnnotatedString {
    val spanned = HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)
    return buildAnnotatedString {
        val text = spanned.toString()
        append(text)
        val spans = spanned.getSpans(0, text.length, Any::class.java)
        for (span in spans) {
            val start = spanned.getSpanStart(span)
            val end = spanned.getSpanEnd(span)
            when (span) {
                is StyleSpan -> when (span.style) {
                    Typeface.BOLD -> addStyle(
                        SpanStyle(fontWeight = FontWeight.Bold),
                        start, end
                    )
                    Typeface.ITALIC -> addStyle(
                        SpanStyle(fontStyle = FontStyle.Italic),
                        start, end
                    )
                    Typeface.BOLD_ITALIC -> addStyle(
                        SpanStyle(fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic),
                        start, end
                    )
                }
                is UnderlineSpan -> addStyle(
                    SpanStyle(textDecoration = TextDecoration.Underline),
                    start, end
                )
                is StrikethroughSpan -> addStyle(
                    SpanStyle(textDecoration = TextDecoration.LineThrough),
                    start, end
                )
                is ForegroundColorSpan -> addStyle(
                    SpanStyle(color = Color(span.foregroundColor)),
                    start, end
                )
                is URLSpan -> {
                    addStyle(
                        SpanStyle(
                            color = ColorInteraction,
                            textDecoration = TextDecoration.Underline
                        ),
                        start, end
                    )
                    addStringAnnotation(
                        tag = "URL",
                        annotation = span.url,
                        start = start,
                        end = end
                    )
                }
            }
        }
    }
}

/**
 * Utility extensions for UI Kit.
 * isValidImage created to avoid the null check for image when it is optional.
 */

fun Any?.isValidImage(): Boolean = if (this != null && this is Int && this > 0) true else false

fun Any?.getImage(): Int = if (this != null && this is Int) this else 0

fun Any?.getString(): String = if (this != null && this is String) this else ""