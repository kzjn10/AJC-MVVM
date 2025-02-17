package com.anhndt.quote.di

import com.anhndt.quote.data.repository.LocalQuoteRepository
import com.anhndt.quote.data.repository.QuoteRepository
import com.anhndt.quote.domain.usecase.QuoteUseCase
import com.anhndt.quote.domain.usecase.impl.QuoteUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UsecaseModule {
    @Provides
    fun provideQuoteUseCase(
        quoteRepository: QuoteRepository,
        localQuoteRepository: LocalQuoteRepository
    ): QuoteUseCase {
        return QuoteUseCaseImpl(
            quoteRepository = quoteRepository,
            localQuoteRepository = localQuoteRepository
        )
    }

}