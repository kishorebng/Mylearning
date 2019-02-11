package com.kishore.learnkotlin.ui


import android.os.Bundle
import android.view.View
import android.widget.Toast
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
    private lateinit var mteamBViewModel: TeamScoreViewModel
    private lateinit var mteamCLiveViewModel: LiveTeamScore
    private lateinit var teamDScore: TeamScoreObservable
    private lateinit var teamEScore: TeamScoreObservableField
    lateinit var mteamFLiveViewModel: LiveTeamScore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
                R.layout.datbin_activity)
        teamAScore = TeamScore()
        setTeamAbinding()

        mteamBViewModel = ViewModelProviders.of(this).get(TeamScoreViewModel::class.java)
        binding.setTeamBScore(mteamBViewModel)
        setTeamBbinding()

        mteamCLiveViewModel = ViewModelProviders.of(this).get(LiveTeamScore::class.java)
        mteamCLiveViewModel.teamScore.observe(this, object : Observer<Int> {
            override fun onChanged(t: Int?) {
                setTeamCbinding()
            }
        })
        setTeamCbinding()

        teamDScore = TeamScoreObservable()
        setTeamDbinding()

        teamEScore = TeamScoreObservableField()
        setTeamEbinding()

        var clickhandler = ClickHandler()
        binding.setHandler(clickhandler)

        mteamFLiveViewModel = ViewModelProviders.of(this).get(LiveTeamScore::class.java)
        mteamFLiveViewModel.teamScore.observe(this, object : Observer<Int> {
            override fun onChanged(t: Int?) {
                setTeamFbinding()
            }
        })
        setTeamFbinding()

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
        mteamBViewModel.score = mteamBViewModel.score + 1;
    }

    fun updateTeamB(v: View) {
        setTeamBbinding()
    }

    fun addOneForTeamC(v: View) {
        val rnd = Random()
        mteamCLiveViewModel.teamScore.value = rnd.nextInt(10);
    }


    fun addOneForTeamD(v: View) {
        val rnd = Random()
        teamDScore.observableScore = rnd.nextInt(50) + 2
    }


    fun addOneForTeamE(v: View) {
        val rnd = Random()
        teamEScore.score.set(rnd.nextInt(15))
    }

    fun updateTeamE(v: View) {
        setTeamAbinding()
    }


    fun setTeamAbinding() {
        binding.setTeamAScore(teamAScore)
    }

    fun setTeamBbinding() {
        binding.setTeamBScore(mteamBViewModel)
    }

    fun setTeamCbinding() {
        binding.setTeamCScore(mteamCLiveViewModel)
    }

    fun setTeamDbinding() {
        binding.setTeamDScore(teamDScore)
    }

    fun setTeamEbinding() {
        binding.setTeamEScore(teamEScore)
    }

    fun setTeamFbinding() {
        binding.setTeamFScore(mteamFLiveViewModel)
    }


    /**
     * Resets the score for all teams back to 0.
     */
    fun resetScore(v: View) {
        teamAScore = TeamScore()
        setTeamAbinding()

        mteamBViewModel.score = 0;
        setTeamBbinding()

        mteamCLiveViewModel.teamScore.value = 0;

        teamDScore.observableScore = 0;

        teamEScore.score.set(0)

        mteamFLiveViewModel.teamScore.value = 0;
    }


    inner class ClickHandler {

        fun addOneForTeamF(v :View) {
            Toast.makeText(applicationContext,"test",Toast.LENGTH_SHORT).show()
            val rnd = Random()
            mteamFLiveViewModel.teamScore.value = rnd.nextInt(10);
        }
    }
}
