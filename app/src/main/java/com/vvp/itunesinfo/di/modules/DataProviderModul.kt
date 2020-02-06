package com.vvp.itunesinfo.di.modules

import com.vvp.itunesinfo.providers.DataProvider
import dagger.Module
import dagger.Provides


@Module
class DataProviderModul {

    @Provides
    fun provideDataProvider(): DataProvider {
        return DataProvider()
    }
}