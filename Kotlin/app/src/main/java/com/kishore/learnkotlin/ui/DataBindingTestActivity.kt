package com.kishore.learnkotlin.ui


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kishore.learnkotlin.R
import com.kishore.learnkotlin.databinding.DatbinActivityBinding
import com.kishore.learnkotlin.databinding.LiveTeamScore
import com.kishore.learnkotlin.databinding.TeamScore
import com.kishore.learnkotlin.databinding.TeamScoreViewModel
import com.kishore.learnkotlin.viewmodel.livedata.LiveScoreViewModel
import java.util.*

class DataBindingTestActivity : AppCompatActivity() {

    private lateinit var binding: DatbinActivityBinding

    private lateinit var teamScore: TeamScore
    private lateinit var mViewModel: TeamScoreViewModel
    private lateinit var mLiveViewModel: LiveTeamScore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
                R.layout.datbin_activity)
        teamScore = TeamScore()
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
        setTeamCbinding();
    }

    fun addOneForTeamA(v: View) {
        val rnd = Random()
        teamScore = TeamScore()
        teamScore.score = rnd.nextInt(50)
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

    fun setTeamAbinding() {
        binding.setTeamAScore(teamScore)
    }

    fun setTeamBbinding() {
        binding.setTeamBScore(mViewModel)
    }

    fun setTeamCbinding() {
        binding.setTeamCScore(mLiveViewModel)
    }

    /**
     * Resets the score for all teams back to 0.
     */
    fun resetScore(v: View) {
        teamScore = TeamScore()
        setTeamAbinding()
        mViewModel.score = 0;
        setTeamBbinding()
        mLiveViewModel.teamScore.value = 0;
    }
}
