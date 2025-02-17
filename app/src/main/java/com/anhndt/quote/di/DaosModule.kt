package com.anhndt.quote.di

import com.anhndt.quote.data.local.dao.QuoteDAO
import com.anhndt.quote.data.local.dao.QuoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesQuoteDao(quoteDatabase: QuoteDatabase): QuoteDAO {
        return quoteDatabase.quoteDao()
    }
}