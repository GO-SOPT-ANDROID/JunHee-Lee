package org.android.go.sopt.gallery

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.Pager
import org.android.go.sopt.databinding.ItemPagerBinding


class GalleryAdapter(context: Context) :
    ListAdapter<Pager, GalleryAdapter.GalleryViewHolder>(
        diffUtil
    ) {
    private val inflater by lazy { LayoutInflater.from(context) }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GalleryViewHolder {
        val binding = ItemPagerBinding.inflate(inflater, parent, false)
        return GalleryViewHolder(binding)
    }


    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.onbind(getItem(position))
    }


    class GalleryViewHolder(private val binding: ItemPagerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onbind(src: Pager) {
            binding.imgGallery.setImageResource(src.image_ex)
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