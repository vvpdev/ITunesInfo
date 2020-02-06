package com.vvp.itunesinfo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vvp.itunesinfo.R
import com.vvp.itunesinfo.network.SongModel


class AdapterSongList: RecyclerView.Adapter<AdapterSongList.ViewHolder>() {


    // внутренний массив для данных
    private var songList: ArrayList<SongModel> = ArrayList()


    // первоначальная загрузка внешнего массива
    fun setupAdapter(songList: ArrayList<SongModel>){

        this.songList.clear()
        this.songList.addAll(songList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(itemView = LayoutInflater.from(parent.context).inflate(R.layout.song_cell, parent, false))
    }

    override fun getItemCount(): Int {
        return songList.count()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindElements(song = this.songList[position])
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        //UI elements
        private val textTrackNumber: TextView = itemView.findViewById(R.id.textTrackNumber)
        private val textTitleTrack: TextView = itemView.findViewById(R.id.textTitleTrack)

        fun bindElements(song: SongModel){

            textTrackNumber.text = song.trackNumber.toString()
            textTitleTrack.text = song.trackName
        }
    }
}