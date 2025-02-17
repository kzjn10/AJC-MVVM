package com.anhndt.quote.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.anhndt.quote.domain.model.Quote
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDAO {

    @Query("SELECT * FROM quote")
    fun getQuotes(): Flow<List<Quote>?>

    @Upsert
    fun insertQuote(vararg quote: Quote)

    @Delete
    fun deleteQuote(quote: Quote)
}