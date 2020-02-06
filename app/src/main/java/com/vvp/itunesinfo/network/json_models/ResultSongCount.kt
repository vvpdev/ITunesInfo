package com.vvp.itunesinfo.network.json_models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class ResultSongCount {

//    @SerializedName("resultCount")
//    @Expose
//    var resultCount: Int? = null
    @SerializedName("results")
    @Expose
    var results: List<ResultSong>? = null

}