package com.example.parcialpdm.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.parcialpdm.DAO.MatchDao
import com.example.parcialpdm.Database.Entities.Match


class MatchRepository(private val matchDao: MatchDao) {

    @WorkerThread
    suspend fun insertMatch(match: Match) {
        matchDao.insertMatch(match)
    }

    fun getAllMatches(): LiveData<List<Match>> = matchDao.getAllMatches()

    fun deleteMatch(id: Int) = matchDao.deleteMatch(id)

    fun deleteAllMatches() = matchDao.deleteAllMatches()
}