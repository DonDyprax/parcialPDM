package com.example.parcialpdm.Activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.parcialpdm.R
import com.example.parcialpdm.Utils.*
import kotlinx.android.synthetic.main.new_match.*
import java.text.SimpleDateFormat
import java.util.*

class NewMatchActivity : AppCompatActivity() {
    private val addActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_match)

        btn_start_match.setOnClickListener {
            if(et_home_team.text.isEmpty() || et_away_team.text.isEmpty()){
                Toast.makeText(this,"Ingrese los nombres de los equipos", Toast.LENGTH_SHORT).show()
            }
            else {
                startActivityForResult(
                        Intent(this, MatchActivity::class.java)
                                .putExtra(KEY_HOME_TEAM, et_home_team.text.toString())
                                .putExtra(KEY_AWAY_TEAM, et_away_team.text.toString())
                                .putExtra(KEY_DATE, getCurrentDate()) ,addActivityRequestCode)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)
        if (requestCode == addActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val replyIntent = Intent()
                replyIntent.putExtra(KEY_HOME_TEAM, data.getStringExtra(KEY_HOME_TEAM))
                replyIntent.putExtra(KEY_AWAY_TEAM, data.getStringExtra(KEY_AWAY_TEAM))
                replyIntent.putExtra(KEY_HOME_SCORE, data.getStringExtra(KEY_HOME_SCORE))
                replyIntent.putExtra(KEY_AWAY_SCORE, data.getStringExtra(KEY_AWAY_SCORE))
                replyIntent.putExtra(KEY_DATE, data.getStringExtra(KEY_DATE))
                setResult(Activity.RESULT_OK, replyIntent)
                finish()
            }
        }
    }

    fun getCurrentDate(): String {
        var date = Date()
        var formatter = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        return formatter.format(date)
    }

}