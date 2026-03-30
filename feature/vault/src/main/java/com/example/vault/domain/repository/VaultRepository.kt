package com.example.vault.domain.repository

import com.example.vault.domain.Entry
import kotlinx.coroutines.flow.Flow

interface VaultRepository {
    fun getAllEntries(): Flow<List<Entry>>
    suspend fun addEntry(entry: Entry)
    suspend fun updateEntry(entry: Entry)
    suspend fun deleteEntry(entry: Entry)
    suspend fun getEntryById(id: Long): Entry?
}