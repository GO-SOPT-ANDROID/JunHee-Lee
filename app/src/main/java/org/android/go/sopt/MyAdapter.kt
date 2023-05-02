package org.android.go.sopt

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.ItemMusicBinding

class MyAdapter(context: Context) : ListAdapter<Music, MyAdapter.MusicViewHolder>(diffUtil) {
    private val inflater by lazy { LayoutInflater.from(context) }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val binding = ItemMusicBinding.inflate(inflater, parent, false)
        return MusicViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    class MusicViewHolder(private val binding: ItemMusicBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: Music) {
            binding.tvMusicname.text = item.music_name
            binding.tvArtistname.text = item.artist_name
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Music>() {
            override fun areItemsTheSame(oldItem: Music, newItem: Music): Boolean {
                return oldItem.artist_name == newItem.artist_name
            }

            override fun areContentsTheSame(oldItem: Music, newItem: Music): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    }
}