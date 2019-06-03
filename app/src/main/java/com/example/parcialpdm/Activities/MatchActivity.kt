package com.example.parcialpdm.Activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.parcialpdm.R
import com.example.parcialpdm.Utils.*
import com.example.parcialpdm.ScoreViewModel
import com.example.parcialpdm.databinding.MatchBinding
import kotlinx.android.synthetic.main.match.*

class MatchActivity : AppCompatActivity() {

    lateinit var scoreViewModel: ScoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scoreViewModel = ViewModelProviders.of(this).get(ScoreViewModel::class.java)

        var binding: MatchBinding = DataBindingUtil.setContentView(this, R.layout.match)
        binding.scores = scoreViewModel

        val homeTeam = intent.extras.getString(KEY_HOME_TEAM)
        val awayTeam = intent.extras.getString(KEY_AWAY_TEAM)
        val date = intent.extras.getString(KEY_DATE)

        home_team.text = homeTeam
        away_team.text = awayTeam

        btn_home_2p.setOnClickListener{
            scoreViewModel.homeTeamScore = (scoreViewModel.homeTeamScore.toInt()+2).toString()
            binding.scores = scoreViewModel
        }

        btn_home_3p.setOnClickListener{
            scoreViewModel.homeTeamScore = (scoreViewModel.homeTeamScore.toInt()+3).toString()
            binding.scores = scoreViewModel
        }

        btn_home_ft.setOnClickListener{
            scoreViewModel.homeTeamScore = (scoreViewModel.homeTeamScore.toInt()+1).toString()
            binding.scores = scoreViewModel
        }

        btn_away_2p.setOnClickListener{
            scoreViewModel.awayTeamScore = (scoreViewModel.awayTeamScore.toInt()+2).toString()
            binding.scores = scoreViewModel
        }

        btn_away_3p.setOnClickListener {
            scoreViewModel.awayTeamScore = (scoreViewModel.awayTeamScore.toInt() + 3).toString()
            binding.scores = scoreViewModel
        }

        btn_away_ft.setOnClickListener{
            scoreViewModel.awayTeamScore = (scoreViewModel.awayTeamScore.toInt()+1).toString()
            binding.scores = scoreViewModel
        }

        btn_reset.setOnClickListener {
            scoreViewModel.homeTeamScore = "0"
            scoreViewModel.awayTeamScore = "0"
            binding.scores = scoreViewModel
        }

        btn_save.setOnClickListener {
            val replyIntent = Intent()
            replyIntent.putExtra(KEY_HOME_TEAM, homeTeam)
            replyIntent.putExtra(KEY_AWAY_TEAM, awayTeam)
            replyIntent.putExtra(KEY_HOME_SCORE, scoreViewModel.homeTeamScore)
            replyIntent.putExtra(KEY_AWAY_SCORE, scoreViewModel.awayTeamScore)
            replyIntent.putExtra(KEY_DATE, date)
            setResult(Activity.RESULT_OK, replyIntent)
            finish()
        }
    }

}