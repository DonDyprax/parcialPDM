package com.example.parcialpdm.Database.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.parcialpdm.DAO.MatchDao
import com.example.parcialpdm.Database.Entities.Match

@Database(entities = [Match::class],version = 1,exportSchema = false)
abstract class MatchDatabase: RoomDatabase() {
    abstract fun matchDao() : MatchDao

    companion object {
        @Volatile
        private var INSTANCE: MatchDatabase? = null

        fun getInstance(context: Context): MatchDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
            synchronized(this) {
                val instance = Room
                    .databaseBuilder(context, MatchDatabase::class.java, "BKBMatchDB")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}