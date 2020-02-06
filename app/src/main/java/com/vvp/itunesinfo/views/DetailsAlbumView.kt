package com.vvp.itunesinfo.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.vvp.itunesinfo.network.AlbumModel
import com.vvp.itunesinfo.network.SongModel


@StateStrategyType(value = AddToEndSingleStrategy::class)
interface DetailsAlbumView: MvpView {


    fun fetchAlbumData()

    fun isLoading(show: Boolean)

    fun showAlbumInfo(songList: ArrayList<SongModel>, album: AlbumModel)

    fun showLoadSongError(error: Int)

}




