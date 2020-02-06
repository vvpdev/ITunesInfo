package com.vvp.itunesinfo.network.retrofit

import com.vvp.itunesinfo.network.json_models.ResultAlbumCount
import com.vvp.itunesinfo.network.json_models.ResultSongCount
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServicesApi {

    // получение списка альбомов
    @GET("/search?&entity=album")
    suspend fun getAlbumList(@Query("term") term: String): Response<ResultAlbumCount>

    // получение списка песен
    @GET("/search?&entity=song&limit=40")
    suspend fun getSongList(@Query("term") term: String): Response<ResultSongCount>
}