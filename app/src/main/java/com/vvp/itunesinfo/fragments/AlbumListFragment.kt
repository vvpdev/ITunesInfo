package com.vvp.itunesinfo.fragments


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.vvp.itunesinfo.R
import com.vvp.itunesinfo.adapters.AdapterAlbumList
import com.vvp.itunesinfo.network.AlbumModel
import com.vvp.itunesinfo.presenters.AlbumListPresenter
import com.vvp.itunesinfo.views.AlbumListView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_album_list.*

class AlbumListFragment : MvpAppCompatFragment(), AlbumListView, AdapterAlbumList.onAlbumClick {


    @InjectPresenter
    lateinit var presenter: AlbumListPresenter

    private lateinit var adapter: AdapterAlbumList
    private lateinit var manager: LinearLayoutManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_album_list, container, false)
    }

    @SuppressLint("CommitPrefEdits")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // изначально пустой заголовок
        activity!!.toolbar.title = ""

        // настройка recyclerView
        manager = LinearLayoutManager(activity!!.applicationContext, LinearLayoutManager.VERTICAL, false)
        adapter = AdapterAlbumList(this)
        recyclerAlbumList.layoutManager = manager
        recyclerAlbumList.adapter = adapter
    }





    //View implementation

    override fun showError(message: Int) {
        Toast.makeText(activity, getText(message), Toast.LENGTH_SHORT).show()
    }


    // показ прогресса загрузки
    override fun isLoading(show: Boolean) {
        if (show) {
            progressLoadingAlbumList.visibility = View.VISIBLE
            recyclerAlbumList.visibility = View.GONE
        } else {
            progressLoadingAlbumList.visibility = View.GONE
            recyclerAlbumList.visibility = View.VISIBLE
        }
    }

    // полчение данных через bundle и передача презентеру
    override fun fetchDataForSearch() {
        presenter.startSearching(arguments!!.getString("textForSearch", ""))
    }


    // получение массива с загруженными альбомами
    override fun fetchAlbumList(albumList: ArrayList<AlbumModel>) {
        activity!!.toolbar.title = getText(R.string.search_result_title)
        adapter.setupAdapter(albumList)
    }


    override fun goToHomeScreen() {
        findNavController().popBackStack()
    }


    // клик по выбранному альбому
    override fun onClick(view: View, album: AlbumModel) {

        // флаг
        presenter.isLoad = true

        val bundle = Bundle()
        bundle.putParcelable("selectedAlbum", album)
        findNavController().navigate(R.id.action_to_detailsAlbumFragment, bundle)
    }




    //save scroll position

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("recState", manager.onSaveInstanceState())
    }


    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        if (savedInstanceState != null){
            manager.onRestoreInstanceState(savedInstanceState.getParcelable("recState"))
        }
    }
}
