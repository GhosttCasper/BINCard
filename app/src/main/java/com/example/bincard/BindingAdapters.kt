package com.example.bincard

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.paris.extensions.style
import com.example.bincard.network.BinModel
import com.example.bincard.ui.adapter.BinListAdapter
import com.example.bincard.ui.viewmodel.BinApiStatus

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

/**
 * Updates the data shown in the [RecyclerView]
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<BinModel>?) {
    val adapter = recyclerView.adapter as BinListAdapter
    adapter.submitList(data)
}

@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: BinApiStatus) {
    when (status) {
        BinApiStatus.LOADING
        -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        BinApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        BinApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}