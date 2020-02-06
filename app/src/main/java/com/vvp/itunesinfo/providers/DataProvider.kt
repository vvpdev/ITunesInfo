package com.vvp.itunesinfo.providers

import android.util.Log
import com.vvp.itunesinfo.App
import com.vvp.itunesinfo.network.json_models.ResultAlbum
import com.vvp.itunesinfo.network.json_models.ResultSong
import com.vvp.itunesinfo.network.retrofit.RetrofitFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject

class DataProvider {


    @Inject
    lateinit var retrofitFactory: RetrofitFactory


    init {
        App.diComponent!!.injectDataProvider(this)
    }

    // список альбомов
    suspend fun getAlbumList(term: String): Deferred<ArrayList<ResultAlbum>>{

        return CoroutineScope(Dispatchers.IO).async {

            var data: ArrayList<ResultAlbum> = ArrayList()

            try {

                val response = retrofitFactory.getServicesApi().getAlbumList(term = term)

                if (response.isSuccessful){

                    data = response.body()!!.results as ArrayList<ResultAlbum>
                }
                else{
                    Log.i("iTunesInfo", "response is fail")
                }
            }
            catch (e: Exception){
                Log.i("iTunesInfo", "network is not available")
            }
            return@async data
        }
    }




    //список песен
    suspend fun getSongList(term: String): Deferred<ArrayList<ResultSong>>{

        return CoroutineScope(Dispatchers.IO).async {

            var data: ArrayList<ResultSong> = ArrayList()

            try {

                val response = retrofitFactory.getServicesApi().getSongList(term = term)

                if (response.isSuccessful){

                    data = response.body()!!.results as ArrayList<ResultSong>
                }
                else{
                    Log.i("iTunesInfo", "response is fail")
                }
            }
            catch (e: Exception){
                Log.i("iTunesInfo", "network is not available")
            }
            return@async data
        }
    }

}