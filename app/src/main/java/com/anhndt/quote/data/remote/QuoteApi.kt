package com.anhndt.quote.data.remote

import com.anhndt.quote.data.model.QuotesResponse
import retrofit2.http.GET

interface QuoteApi {
    @GET("/quotes")
    suspend fun getQuotes(): QuotesResponse
}