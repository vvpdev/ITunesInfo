package com.vvp.itunesinfo.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.vvp.itunesinfo.network.AlbumModel


@StateStrategyType(value = AddToEndSingleStrategy::class)
interface AlbumListView : MvpView {


    fun showError(message: Int)

    fun isLoading(show: Boolean)

    fun fetchDataForSearch()

    fun fetchAlbumList(albumList: ArrayList<AlbumModel>)

    fun goToHomeScreen()
}