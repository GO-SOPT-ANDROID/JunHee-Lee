package org.android.go.sopt

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.ItemMusicBinding

class MyAdapter(context: Context) : RecyclerView.Adapter<MyAdapter.MusicViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }

    private var itemlist: List<Music> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val binding = ItemMusicBinding.inflate(inflater, parent, false)
        return MusicViewHolder(binding)
    }

    override fun getItemCount() = itemlist.size

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.onBind(itemlist[position])
    }

    class MusicViewHolder(private val binding: ItemMusicBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: Music) {
            binding.tvMusicname.text = item.music_name
            binding.tvArtistname.text = item.artist_name
        }
        }

    fun setmusicList(musicList: List<Music>) {
        this.itemlist = musicList.toList()
        notifyDataSetChanged()
    }
}