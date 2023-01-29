package com.example.bincard.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bincard.databinding.ListViewItemBinding
import com.example.bincard.network.BinModel

/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 */
class BinListAdapter(val clickListener: BinListener) :
    ListAdapter<BinModel, BinListAdapter.BinViewHolder>(DiffCallback) {
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

    class BinViewHolder(
        private var binding: ListViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: BinListener, bin: BinModel) {
            binding.bin = bin
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<BinModel>() {
        override fun areItemsTheSame(oldItem: BinModel, newItem: BinModel): Boolean {
            return oldItem.bin == newItem.bin
        }

        override fun areContentsTheSame(oldItem: BinModel, newItem: BinModel): Boolean {
            return oldItem.scheme == newItem.scheme && oldItem.type == newItem.type
                    && oldItem.brand == newItem.brand && oldItem.prepaid == newItem.prepaid
                    && oldItem.number == newItem.number && oldItem.number.length == newItem.number.length
                    && oldItem.country == newItem.country && oldItem.bank == newItem.bank
        }
    }
}

class BinListener(val clickListener: (bin: BinModel) -> Unit) {
    fun onClick(bin: BinModel) = clickListener(bin)
}