package com.example.parcialpdm.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.parcialpdm.Database.Entities.Match

@Dao
interface MatchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatch(match: Match)

    @Query("SELECT * FROM match_table")
    fun getAllMatches() : LiveData<List<Match>>

    @Query("DELETE FROM match_table WHERE id = :id")
    fun deleteMatch(id:Int)

    @Query("DELETE FROM match_table")
    fun deleteAllMatches()

}