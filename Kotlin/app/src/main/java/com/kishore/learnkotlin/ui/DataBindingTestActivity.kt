package com.kishore.learnkotlin.ui


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.kishore.learnkotlin.R
import com.kishore.learnkotlin.databinding.TeamScore
import com.kishore.learnkotlin.databinding.DbActvityBinding

class DataBindingTestActivity : AppCompatActivity() {

    //track team A score
    private lateinit var team: TeamScore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : DbActvityBinding = DataBindingUtil.setContentView(this,
                R.layout.db_actvity)
        team = TeamScore()
        team.teamScore = 10
    }

    fun addOneForTeamA(v: View) {
    }

    fun addOneForTeamB(v: View) {
    }

    fun addOneForTeamC(v: View) {
    }

}
