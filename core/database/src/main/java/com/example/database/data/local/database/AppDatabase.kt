package com.example.database.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.data.local.dao.VaultDao
import com.example.database.data.local.entity.VaultEntity

@Database(
    entities = [VaultEntity::class],
    version =  1,
    exportSchema = false
)

abstract class AppDatabase: RoomDatabase() {
    abstract fun vaultDao(): VaultDao
}