package com.anhndt.quote.data.repository.impl

import com.anhndt.quote.data.remote.QuoteApi
import com.anhndt.quote.data.repository.QuoteRepository
import com.anhndt.quote.domain.model.Quote
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    private val quoteApi: QuoteApi
) : QuoteRepository {
    override suspend fun getQuotes(): List<Quote> {
        val res = quoteApi.getQuotes()
        return res.quotes
    }
}