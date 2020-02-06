package com.vvp.itunesinfo.di.modules

import com.vvp.itunesinfo.network.retrofit.RetrofitFactory
import dagger.Module
import dagger.Provides


@Module
class RetrofitModul {

    @Provides
    fun provideRetrofit(): RetrofitFactory{
        return RetrofitFactory()
    }
}