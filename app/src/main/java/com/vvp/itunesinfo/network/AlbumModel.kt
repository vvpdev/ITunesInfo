package com.vvp.itunesinfo.network

import android.os.Parcel
import android.os.Parcelable

data class AlbumModel (

    var artistName: String? = null,

    var collectionName: String? = null,

    var imageAlbumUrl: String? = null,

    var trackCount: Int? = null,

    var copyright: String? = null,

    var country: String? = null,

    var releaseDate: String? = null,

    var primaryGenreName: String? = null,

    var collectionId: Int? = null

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(artistName)
        parcel.writeString(collectionName)
        parcel.writeString(imageAlbumUrl)
        parcel.writeValue(trackCount)
        parcel.writeString(copyright)
        parcel.writeString(country)
        parcel.writeString(releaseDate)
        parcel.writeString(primaryGenreName)
        parcel.writeValue(collectionId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AlbumModel> {
        override fun createFromParcel(parcel: Parcel): AlbumModel {
            return AlbumModel(parcel)
        }

        override fun newArray(size: Int): Array<AlbumModel?> {
            return arrayOfNulls(size)
        }
    }
}