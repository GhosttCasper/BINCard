package com.example.bincard.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bincard.databinding.ListViewItemBinding
import com.example.bincard.network.Bin

/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 */
class BinListAdapter(val clickListener: BinListener) :
    ListAdapter<Bin, BinListAdapter.BinViewHolder>(DiffCallback) {

    class BinViewHolder(
        var binding: ListViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: BinListener, bin: Bin) {
            binding.bin = bin
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Bin>() {
        override fun areItemsTheSame(oldItem: Bin, newItem: Bin): Boolean {
            return oldItem.bin == newItem.bin
        }

        override fun areContentsTheSame(oldItem: Bin, newItem: Bin): Boolean {
            return oldItem.scheme == newItem.scheme && oldItem.type == newItem.type
                    && oldItem.brand == newItem.brand && oldItem.prepaid == newItem.prepaid
                    && oldItem.number?.luhn == newItem.number?.luhn && oldItem.number?.length == newItem.number?.length
                    && oldItem.country == newItem.country && oldItem.bank == newItem.bank
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BinViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BinViewHolder(
            ListViewItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BinViewHolder, position: Int) {
        val bin = getItem(position)
        holder.bind(clickListener, bin)
    }
}

class BinListener(val clickListener: (bin: Bin) -> Unit) {
    fun onClick(bin: Bin) = clickListener(bin)
}