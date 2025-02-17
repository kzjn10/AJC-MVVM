package com.anhndt.quote.domain.usecase

import com.anhndt.quote.domain.model.Quote

interface QuoteUseCase {
    suspend fun getQuotes(isForceRefresh: Boolean): Result<List<Quote>?>
}