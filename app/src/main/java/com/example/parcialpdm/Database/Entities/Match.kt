package com.example.parcialpdm.Database.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "match_table")
data class Match(
    val homeTeam: String,
    val awayTeam: String,
    val homeTeamScore: Int,
    val awayTeamScore: Int,
    val date: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}