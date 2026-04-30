package com.example.database.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.database.data.local.entity.VaultEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VaultDao {

    @Query("SELECT * FROM vaults ORDER BY createdAt DESC")
    fun getAllVaults(): Flow<List<VaultEntity>>

    @Query("SELECT * FROM vaults WHERE id = :id")
    suspend fun getVaultById(id: Long): VaultEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vault: VaultEntity): Long

    @Update
    suspend fun update(vault: VaultEntity)

    @Query("DELETE FROM vaults WHERE id = :id")
    suspend fun deleteById(id: Long)


}