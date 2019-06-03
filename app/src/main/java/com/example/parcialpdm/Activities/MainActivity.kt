package com.example.parcialpdm

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.parcialpdm.Activities.MatchDetailsActivity
import com.example.parcialpdm.Activities.NewMatchActivity
import com.example.parcialpdm.Database.Entities.Match
import com.example.parcialpdm.Fragments.MatchFragment
import com.example.parcialpdm.Utils.*
import com.example.parcialpdm.ViewModel.MatchViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MatchFragment.clickListener {
    private val addActivityRequestCode = 1
    lateinit var viewModel: MatchViewModel
    private lateinit var fragment: MatchFragment
    private lateinit var matches: List<Match>

    override fun itemClick(match: Match) {
        startActivity(
            Intent(this, MatchDetailsActivity::class.java)
                .putExtra(KEY_DATE, match.date)
                .putExtra(KEY_HOME_TEAM, match.homeTeam)
                .putExtra(KEY_AWAY_TEAM, match.awayTeam)
                .putExtra(KEY_HOME_SCORE, match.homeTeamScore)
                .putExtra(KEY_AWAY_SCORE, match.awayTeamScore)
        )
    }

    override fun delete(match: Match) {
        viewModel.deleteMatch(match.id)
        Toast.makeText(this,"the game has been removed", Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragment()
        viewModel = ViewModelProviders.of(this).get(MatchViewModel::class.java)
        viewModel.getAllMatches().observe(this, Observer {
            if(it != null){
                this.matches = it
                fragment.updateAdapter(it)
            }
        })

        new_match.setOnClickListener {
            val intent = Intent(this@MainActivity, NewMatchActivity::class.java)
            startActivityForResult(intent, addActivityRequestCode)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)
        if (requestCode == addActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val match = Match(
                    data.getStringExtra(KEY_HOME_TEAM),
                    data.getStringExtra(KEY_AWAY_TEAM),
                    data.getStringExtra(KEY_HOME_SCORE).toInt(),
                    data.getStringExtra(KEY_AWAY_SCORE).toInt(),
                    data.getStringExtra(KEY_DATE))
                viewModel.insertMatch(match)
            }
        } else {
            Toast.makeText(applicationContext,"", Toast.LENGTH_LONG).show()
        }
    }

    private fun initFragment(){
        fragment = MatchFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, fragment).commit()
    }
}
