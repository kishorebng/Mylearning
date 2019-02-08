package com.kishore.learnkotlin.ui


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kishore.learnkotlin.R
import com.kishore.learnkotlin.databinding.*
import java.util.*

class DataBindingTestActivity : AppCompatActivity() {

    private lateinit var binding: DatbinActivityBinding

    private lateinit var teamAScore: TeamScore
    private lateinit var mViewModel: TeamScoreViewModel
    private lateinit var mLiveViewModel: LiveTeamScore
    private lateinit var teamDScore: TeamScoreObservable



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
                R.layout.datbin_activity)
        teamAScore = TeamScore()
        setTeamAbinding()

        mViewModel = ViewModelProviders.of(this).get(TeamScoreViewModel::class.java)
        binding.setTeamBScore(mViewModel)
        setTeamBbinding()

        mLiveViewModel = ViewModelProviders.of(this).get(LiveTeamScore::class.java)
        mLiveViewModel.teamScore.observe(this, object : Observer<Int> {
            override fun onChanged(t: Int?) {
                setTeamCbinding()
            }
        })
        setTeamCbinding()

        teamDScore = TeamScoreObservable()
        setTeamDbinding()
    }

    fun addOneForTeamA(v: View) {
        val rnd = Random()
        teamAScore = TeamScore()
        teamAScore.score = rnd.nextInt(50)
    }

    fun updateTeamA(v: View) {
        setTeamAbinding()
    }

    fun addOneForTeamB(v: View) {
        mViewModel.score = mViewModel.score + 1;
    }

    fun updateTeamB(v: View) {
        setTeamBbinding()
    }

    fun addOneForTeamC(v: View) {
        val rnd = Random()
        mLiveViewModel.teamScore.value = rnd.nextInt(10);
    }


    fun addOneForTeamD(v: View) {
        val rnd = Random()
        teamDScore = TeamScoreObservable()
        teamDScore.observableScore = rnd.nextInt(50) + 2
    }


    fun addOneForTeamE(v: View) {
    }

    fun updateTeamE(v: View) {
        setTeamAbinding()
    }

    fun addOneForTeamF(v: View) {
    }


    fun setTeamAbinding() {
        binding.setTeamAScore(teamAScore)
    }

    fun setTeamBbinding() {
        binding.setTeamBScore(mViewModel)
    }

    fun setTeamCbinding() {
        binding.setTeamCScore(mLiveViewModel)
    }

    fun setTeamDbinding() {
        binding.setTeamDScore(teamDScore)
    }


    /**
     * Resets the score for all teams back to 0.
     */
    fun resetScore(v: View) {
        teamAScore = TeamScore()
        setTeamAbinding()
        mViewModel.score = 0;
        setTeamBbinding()
        mLiveViewModel.teamScore.value = 0;
    }
}
