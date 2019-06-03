package com.example.parcialpdm.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.parcialpdm.Database.Entities.Match
import com.example.parcialpdm.Database.Room.MatchDatabase
import com.example.parcialpdm.Repository.MatchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MatchViewModel(application: Application) : AndroidViewModel(application) {
    private val matchRepository: MatchRepository

    init {
        val matchDao = MatchDatabase.getInstance(application).matchDao()
        matchRepository = MatchRepository(matchDao)
    }

    fun insertMatch(match : Match) = viewModelScope.launch(Dispatchers.IO){
        matchRepository.insertMatch(match)
    }

    fun getAllMatches() : LiveData<List<Match>> = matchRepository.getAllMatches()

    fun deleteMatch(id: Int) = viewModelScope.launch(Dispatchers.IO){
        matchRepository.deleteMatch(id)
    }

    fun deleteAllMatches() = viewModelScope.launch(Dispatchers.IO){
        matchRepository.deleteAllMatches()
    }
}