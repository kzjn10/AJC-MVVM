package com.anhndt.quote.data.repository.impl

import com.anhndt.quote.data.local.dao.QuoteDAO
import com.anhndt.quote.data.repository.LocalQuoteRepository
import com.anhndt.quote.domain.model.Quote
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalQuoteRepositoryImpl @Inject constructor(
    private val quote: QuoteDAO
) : LocalQuoteRepository {
    override suspend fun getAllQuotes(): Flow<List<Quote>?> {
        return quote.getQuotes()
    }

    override suspend fun saveQuotes(quotes: List<Quote>) {
        quotes.forEach {
            quote.insertQuote(it)
        }
    }
}