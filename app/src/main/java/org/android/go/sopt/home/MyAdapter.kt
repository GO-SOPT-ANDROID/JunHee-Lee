package org.android.go.sopt.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.android.go.sopt.databinding.ItemHomeBinding
import org.android.go.sopt.model.ResponseHome

class MyAdapter(context: Context) : ListAdapter<ResponseHome.Data, MyAdapter.MusicViewHolder>(diffUtil) {
    private val inflater by lazy { LayoutInflater.from(context) }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val binding = ItemHomeBinding.inflate(inflater, parent, false)
        return MusicViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class MusicViewHolder(private val binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: ResponseHome.Data) {
            binding.ivUser.load(item.avatar)
            binding.tvUsername.text = item.first_name
            binding.tvUseremail.text = item.email
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ResponseHome.Data>() {
            override fun areItemsTheSame(oldItem: ResponseHome.Data, newItem: ResponseHome.Data): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ResponseHome.Data, newItem: ResponseHome.Data): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    }
}