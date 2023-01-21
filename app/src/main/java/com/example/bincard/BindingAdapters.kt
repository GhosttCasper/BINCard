package com.example.bincard

import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.airbnb.paris.extensions.style

@BindingAdapter(value = ["htmlText"])
fun TextView.setHtmlText(string: String?) {
    text = HtmlCompat.fromHtml(string ?: "", HtmlCompat.FROM_HTML_MODE_COMPACT)
}

@BindingAdapter("isSelectedStyle")
fun setStyle(textView: TextView, isSelected: Boolean) {
    if (isSelected) {
        textView.style(R.style.Widget_BINCard_TextView_Detail)
    } else {
        textView.style(R.style.Widget_BINCard_TextView_InnerLabel)
    }
}