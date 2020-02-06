package com.vvp.itunesinfo.converters

import com.vvp.itunesinfo.network.AlbumModel
import com.vvp.itunesinfo.network.SongModel
import com.vvp.itunesinfo.network.json_models.ResultAlbum
import com.vvp.itunesinfo.network.json_models.ResultSong


    // конвертирование json моделей в data классы

class DataConverter {


    fun convertAlbumList(responseList: ArrayList<ResultAlbum>): ArrayList<AlbumModel> {

        val albumList: ArrayList<AlbumModel> = ArrayList()

        if (!responseList.isNullOrEmpty() && responseList.count() != 0) {

            responseList.forEach {
                albumList.add(
                    AlbumModel(
                        artistName = it.artistName,
                        collectionName = it.collectionName,
                        imageAlbumUrl = it.artworkUrl100,
                        trackCount = it.trackCount,
                        copyright = it.copyright,
                        country = it.country,
                        releaseDate = it.releaseDate,
                        primaryGenreName = it.primaryGenreName,
                        collectionId = it.collectionId
                    )
                )
            }
        }

        //редактирование формата даты
        albumList.forEach {

            val indexT = it.releaseDate!!.indexOf("T")
            val lastIndex = it.releaseDate!!.lastIndex

            val dateNew = (it.releaseDate!!.removeRange(indexT, lastIndex)).removeSuffix("Z")

            it.releaseDate = dateNew
        }

        // сортировка по названию альбома
        albumList.sortBy { it.collectionName }

        return albumList
    }



    fun convertSongList(responseList: ArrayList<ResultSong>, album: AlbumModel): ArrayList<SongModel>{

        val songList: ArrayList<SongModel> = ArrayList()

            responseList.forEach {

                // проверка принадлежности песни выбранному альбому по collectionId
                if (it.collectionId == album.collectionId) {
                    songList.add( SongModel( it.trackNumber, it.trackName ))
                }
            }

        // сортировка по номеру трека
        songList.sortBy { it.trackNumber }

        return songList
    }
}