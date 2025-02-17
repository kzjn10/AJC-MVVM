package com.anhndt.quote.data.repository

import com.anhndt.quote.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface LocalQuoteRepository {
    suspend fun getAllQuotes(): Flow<List<Quote>?>

    suspend fun saveQuotes(quotes: List<Quote>)
}