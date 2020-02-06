package com.vvp.itunesinfo.fragments


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.Glide
import com.vvp.itunesinfo.R
import com.vvp.itunesinfo.adapters.AdapterSongList
import com.vvp.itunesinfo.network.AlbumModel
import com.vvp.itunesinfo.network.SongModel
import com.vvp.itunesinfo.presenters.DetailsAlbumPresenter
import com.vvp.itunesinfo.views.DetailsAlbumView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_details_album.*


@Suppress("DEPRECATION")
class DetailsAlbumFragment : MvpAppCompatFragment(), DetailsAlbumView {


    @InjectPresenter
    lateinit var presenter: DetailsAlbumPresenter


    private lateinit var adapter: AdapterSongList
    private lateinit var manager: LinearLayoutManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_album, container, false)
    }


    @SuppressLint("CommitPrefEdits")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = AdapterSongList()
        manager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        recyclerViewSongs.layoutManager = manager
        recyclerViewSongs.adapter = adapter

        // отключение прокрутки
        recyclerViewSongs.isLayoutFrozen = true

        // отключение фокуса на список
        recyclerViewSongs.isFocusable = false
    }



    // получение выбранного альбома и передача презентеру для загрузки списка песен
    override fun fetchAlbumData() {
        val selectedAlbum: AlbumModel = arguments!!.getParcelable("selectedAlbum")!!
        presenter.getSongList(album = selectedAlbum)
        activity!!.toolbar.title = selectedAlbum.collectionName
    }

    // показ прогресса
    override fun isLoading(show: Boolean) {
        if (show) {
            progressLoadingDetails.visibility = View.VISIBLE
            textAlbumTitleDet.visibility = View.GONE
            textArtistNameDet.visibility = View.GONE
            imageAlbumDet.visibility = View.GONE
            textTitleCopyright.visibility = View.GONE
            textCopyrightDet.visibility = View.GONE
            textTitleRelease.visibility = View.GONE
            textReleaseDateDet.visibility = View.GONE
            textTitleCountry.visibility = View.GONE
            textCountryDet.visibility = View.GONE
            textTitleGenreName.visibility = View.GONE
            textPrimaryGenreNameDet.visibility = View.GONE
            textTracklist.visibility = View.GONE
            recyclerViewSongs.visibility = View.GONE
            textLoadSongError.visibility = View.GONE
        } else {
            progressLoadingDetails.visibility = View.GONE
            textAlbumTitleDet.visibility = View.VISIBLE
            textArtistNameDet.visibility = View.VISIBLE
            imageAlbumDet.visibility = View.VISIBLE
            textTitleCopyright.visibility = View.VISIBLE
            textCopyrightDet.visibility = View.VISIBLE
            textTitleRelease.visibility = View.VISIBLE
            textReleaseDateDet.visibility = View.VISIBLE
            textTitleCountry.visibility = View.VISIBLE
            textCountryDet.visibility = View.VISIBLE
            textTitleGenreName.visibility = View.VISIBLE
            textPrimaryGenreNameDet.visibility = View.VISIBLE
            textTracklist.visibility = View.VISIBLE
            recyclerViewSongs.visibility = View.VISIBLE
            textLoadSongError.visibility = View.GONE
        }
    }


    // отображение загруженных данных
    override fun showAlbumInfo(songList: ArrayList<SongModel>, album: AlbumModel) {

        Glide
            .with(activity!!.applicationContext)
            .load(album.imageAlbumUrl)
            .centerCrop()
            .into(imageAlbumDet)

        textAlbumTitleDet.text = album.collectionName
        textArtistNameDet.text = album.artistName
        textCopyrightDet.text = album.copyright
        textReleaseDateDet.text = album.releaseDate
        textCountryDet.text = album.country
        textPrimaryGenreNameDet.text = album.primaryGenreName

        adapter.setupAdapter(songList = songList)
    }


    // показ ошибки загрузки списка песен
    override fun showLoadSongError(error: Int) {
        recyclerViewSongs.visibility = View.GONE
        textLoadSongError.visibility = View.VISIBLE
        textLoadSongError.text = getText(error)
    }
}
