package com.example.parcialpdm.Activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.parcialpdm.R
import com.example.parcialpdm.Utils.*
import kotlinx.android.synthetic.main.match_details.*

class MatchDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.match_details)

        val homeTeam = intent.extras.getString(KEY_HOME_TEAM)
        val awayTeam = intent.extras.getString(KEY_AWAY_TEAM)
        val homeScore = intent.extras.getInt(KEY_HOME_SCORE)
        val awayScore = intent.extras.getInt(KEY_AWAY_SCORE)
        val date = intent.extras.getString(KEY_DATE)

        Log.d("DEB", homeTeam)
        Log.d("DEB", awayTeam)
        Log.d("DEB", homeScore.toString())
        Log.d("DEB", awayScore.toString())
        Log.d("DEB", date)

        if(homeScore > awayScore) {
            tv_home_winner.visibility = View.VISIBLE
        } else {
            tv_away_winner.visibility = View.VISIBLE
        }

        tv_home_team.text = homeTeam
        tv_away_team.text = awayTeam
        tv_home_score.text = homeScore.toString()
        tv_away_score.text = awayScore.toString()
        tv_date.text = date
    }
}