package com.vvp.itunesinfo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vvp.itunesinfo.R
import com.vvp.itunesinfo.network.AlbumModel


class AdapterAlbumList(private val listener: onAlbumClick): RecyclerView.Adapter<AdapterAlbumList.ViewHolder>() {


    interface onAlbumClick{
        fun onClick(view: View, album: AlbumModel)
    }


    // внутренний массив для данных
    private var albumList: ArrayList<AlbumModel> = ArrayList()


    // первоначальная загрузка внешнего массива
    fun setupAdapter(albumList: ArrayList<AlbumModel>){

        this.albumList.clear()

        this.albumList.addAll(albumList)

        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(itemView = LayoutInflater.from(parent.context).inflate(R.layout.album_cell, parent, false))
    }


    override fun getItemCount(): Int {
        return albumList.count()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindElements(album = this.albumList[position], action = listener)
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        //UI elements
        private val imageAlbumCell: ImageView = itemView.findViewById(R.id.imageAlbumCell)
        private val textTitleAlbumCell: TextView = itemView.findViewById(R.id.textTitleAlbumCell)
        private val textNameArtistCell: TextView = itemView.findViewById(R.id.textNameArtistCell)

        fun bindElements(album: AlbumModel, action: onAlbumClick){

            Glide
                .with(itemView)
                .load(album.imageAlbumUrl)
                .centerCrop()
                .into(imageAlbumCell)

            textTitleAlbumCell.text = album.collectionName
            textNameArtistCell.text = album.artistName

            this.imageAlbumCell.setOnClickListener    {  action.onClick(view = itemView, album =  album) }
            this.textTitleAlbumCell.setOnClickListener{  action.onClick(view = itemView, album =  album) }
            this.textNameArtistCell.setOnClickListener{  action.onClick(view = itemView, album =  album) }
        }
    }


}