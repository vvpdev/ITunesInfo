package com.vvp.itunesinfo.di.components

import com.vvp.itunesinfo.di.modules.ConverterModul
import com.vvp.itunesinfo.di.modules.DataProviderModul
import com.vvp.itunesinfo.di.modules.RetrofitModul
import com.vvp.itunesinfo.presenters.AlbumListPresenter
import com.vvp.itunesinfo.presenters.DetailsAlbumPresenter
import com.vvp.itunesinfo.providers.DataProvider
import dagger.Component


@Component (modules = [ConverterModul::class, DataProviderModul::class, RetrofitModul::class])
interface DiComponent {

    fun injectAlbumListPresenter(presenter: AlbumListPresenter)
    fun injectDetailsAlbumPresenter(presenter: DetailsAlbumPresenter)
    fun injectDataProvider(provider: DataProvider)
}