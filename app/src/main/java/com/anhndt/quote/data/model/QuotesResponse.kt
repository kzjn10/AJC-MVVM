package com.anhndt.quote.data.model

import com.anhndt.quote.domain.model.Quote

data class QuotesResponse(
    val quotes: List<Quote>,
)