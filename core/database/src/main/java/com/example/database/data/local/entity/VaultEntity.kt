package com.example.database.data.local.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vaults")
data class VaultEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val appTitle: String,
    val url: String,
    val userName: String,
    val encryptedPassword: ByteArray,
    val initVector: ByteArray,
    val notes: String,
    val createdAt: Long
)