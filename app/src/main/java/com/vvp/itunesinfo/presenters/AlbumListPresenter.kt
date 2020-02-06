package com.vvp.itunesinfo.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vvp.itunesinfo.App
import com.vvp.itunesinfo.R
import com.vvp.itunesinfo.converters.DataConverter
import com.vvp.itunesinfo.network.AlbumModel
import com.vvp.itunesinfo.providers.DataProvider
import com.vvp.itunesinfo.views.AlbumListView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@InjectViewState
class AlbumListPresenter: MvpPresenter<AlbumListView>() {

    @Inject
    lateinit var provider: DataProvider

    @Inject
    lateinit var converter: DataConverter

    private val albumList: ArrayList<AlbumModel> = ArrayList()

    //флаг
    var isLoad: Boolean = false



    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        // инжектирование при первом прикреплении view
        App.diComponent!!.injectAlbumListPresenter(this)

        checkLoad()
    }


    override fun attachView(view: AlbumListView?) {
        super.attachView(view)

        checkLoad()
    }


    // проверка флага загрузки
    private fun checkLoad(){
        if (!isLoad){
            viewState.fetchDataForSearch()
        }
        else{
            viewState.fetchAlbumList(getLoadedAlbumList())
        }
    }


    // загрузка списка альбомов
    fun startSearching(name: String) {

        viewState.isLoading(true)

        CoroutineScope(Dispatchers.IO).launch {

            val responseList = provider.getAlbumList(name).await()

            val loadedAlbumList = converter.convertAlbumList(responseList)

            albumList.clear()
            albumList.addAll(loadedAlbumList)

            if (!albumList.isNullOrEmpty()) {

                // к списку альбомов
                CoroutineScope(Dispatchers.Main).launch {
                    viewState.isLoading(false)
                    viewState.fetchAlbumList(albumList)
                }
            } else {
                CoroutineScope(Dispatchers.Main).launch {
                    viewState.isLoading(false)
                    viewState.showError(R.string.error_search)
                    viewState.goToHomeScreen()
                }
            }
        }
    }


    fun getLoadedAlbumList(): ArrayList<AlbumModel>{
        return albumList
    }
}