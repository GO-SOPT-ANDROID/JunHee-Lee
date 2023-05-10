package org.android.go.sopt.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.ItemHeaderBinding


class HeaderAdapter(context: Context) : RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }

    class HeaderViewHolder(private val binding: ItemHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val binding = ItemHeaderBinding.inflate(inflater, parent, false)
        return HeaderViewHolder(binding)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        return
    }
}