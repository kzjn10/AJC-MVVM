package com.anhndt.quote.data.local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anhndt.quote.domain.model.Quote

@Database(entities = [Quote::class], version = 1)
abstract class QuoteDatabase: RoomDatabase() {
    abstract fun quoteDao(): QuoteDAO
}