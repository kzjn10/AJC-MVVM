package com.anhndt.quote.data.repository

import com.anhndt.quote.domain.model.Quote

interface QuoteRepository {
    suspend fun getQuotes(): List<Quote>
}