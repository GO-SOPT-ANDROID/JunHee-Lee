package org.android.go.sopt

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.ItemPagerBinding


class GalleryAdapter (context: Context, _itemList: List<Int> = listOf()): ListAdapter<Pager, GalleryAdapter.GalleryViewHolder>(GalleryAdapter.diffUtil) {
    private val inflater by lazy { LayoutInflater.from(context)}
    private var itemList1: List<Int> = _itemList

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GalleryAdapter.GalleryViewHolder {
        val binding = ItemPagerBinding.inflate(inflater, parent, false)
        return GalleryAdapter.GalleryViewHolder(binding)
    }


    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(src = R.drawable.pic1)
    }


    class GalleryViewHolder(private val binding: ItemPagerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(src: Int) {
            binding.imgGallery.setImageResource(src)
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Pager>() {
            override fun areItemsTheSame(oldItem: Pager, newItem: Pager): Boolean {
                return oldItem.image_ex == newItem.image_ex
            }

            override fun areContentsTheSame(oldItem: Pager, newItem: Pager): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    }
}