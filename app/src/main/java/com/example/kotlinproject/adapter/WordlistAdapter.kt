/*
Lauri Riikonen
1909911
 */

package com.example.kotlinproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.JsonWord
import com.example.kotlinproject.databinding.ListViewItemBinding

class WordlistAdapter : ListAdapter<JsonWord, WordlistAdapter.JsonWordViewHolder>(DiffCallback) {


    /**
     * The _root_ide_package_.com.example.kotlinproject.JsonWordViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full [_root_ide_package_.com.example.kotlinproject.JsonWord] information.
     */
    class JsonWordViewHolder(private var binding: ListViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(jsonWord: JsonWord) {
            binding.property = jsonWord
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [_root_ide_package_.com.example.kotlinproject.JsonWord]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<JsonWord>() {
        override fun areItemsTheSame(oldItem: JsonWord, newItem: JsonWord): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: JsonWord, newItem: JsonWord): Boolean {
            return oldItem.id == newItem.id
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): JsonWordViewHolder {
        return JsonWordViewHolder(ListViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: JsonWordViewHolder, position: Int) {
        val jsonWordProperty = getItem(position)
        holder.bind(jsonWordProperty)
    }
}