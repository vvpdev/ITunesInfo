package com.vvp.itunesinfo.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vvp.itunesinfo.App
import com.vvp.itunesinfo.R
import com.vvp.itunesinfo.converters.DataConverter
import com.vvp.itunesinfo.network.AlbumModel
import com.vvp.itunesinfo.network.SongModel
import com.vvp.itunesinfo.providers.DataProvider
import com.vvp.itunesinfo.views.DetailsAlbumView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@InjectViewState
class DetailsAlbumPresenter: MvpPresenter<DetailsAlbumView>() {

    @Inject
    lateinit var provider: DataProvider

    @Inject
    lateinit var converter: DataConverter


    override fun attachView(view: DetailsAlbumView?) {
        super.attachView(view)

        viewState.fetchAlbumData()
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        // инжектирование при первом прикреплении view
        App.diComponent!!.injectDetailsAlbumPresenter(this)

        viewState.fetchAlbumData()
    }


    // получение списка песен и отображение информации на макете
    fun getSongList(album: AlbumModel) {

        viewState.isLoading(true)

        CoroutineScope(Dispatchers.IO).launch {

            val songList: ArrayList<SongModel> = ArrayList()


            // поиск списка песен по названию артиста + названию альбома
            // дальнейшая сортировка по id альбома - в конвертере
            val responseList = provider.getSongList(term = (album.artistName + " " + album.collectionName)).await()

            if (!responseList.isNullOrEmpty()) {

                songList.addAll(converter.convertSongList(responseList, album))

                CoroutineScope(Dispatchers.Main).launch {

                    viewState.isLoading(false)
                    viewState.showAlbumInfo(songList = songList, album = album)
                }
            } else {
                CoroutineScope(Dispatchers.Main).launch {
                    viewState.isLoading(false)
                    viewState.showAlbumInfo(songList = songList, album = album)
                    viewState.showLoadSongError(R.string.error_song_load)
                }
            }
        }
    }



}