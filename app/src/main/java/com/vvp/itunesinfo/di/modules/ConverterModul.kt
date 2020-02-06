package com.vvp.itunesinfo.di.modules

import com.vvp.itunesinfo.converters.DataConverter
import dagger.Module
import dagger.Provides

@Module
class ConverterModul {

    @Provides
    fun provideConverter(): DataConverter{
        return DataConverter()
    }
}