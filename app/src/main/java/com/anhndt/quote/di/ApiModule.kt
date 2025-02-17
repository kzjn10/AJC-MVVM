package com.anhndt.quote.di

import com.anhndt.quote.data.local.dao.QuoteDAO
import com.anhndt.quote.data.remote.QuoteApi
import com.anhndt.quote.data.repository.LocalQuoteRepository
import com.anhndt.quote.data.repository.QuoteRepository
import com.anhndt.quote.data.repository.impl.LocalQuoteRepositoryImpl
import com.anhndt.quote.data.repository.impl.QuoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideQuoteRepository(quoteApi: QuoteApi): QuoteRepository {
        return QuoteRepositoryImpl(quoteApi)

    }

    @Provides
    @Singleton
    fun provideLocalQuoteRepository(quoteDao: QuoteDAO): LocalQuoteRepository {
        return LocalQuoteRepositoryImpl(quote = quoteDao)
    }


}