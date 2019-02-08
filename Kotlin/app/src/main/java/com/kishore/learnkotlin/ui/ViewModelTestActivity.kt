package com.kishore.learnkotlin.ui


import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kishore.learnkotlin.R
import com.kishore.learnkotlin.viewmodel.ScoreViewModel
import com.kishore.learnkotlin.viewmodel.livedata.LiveScoreViewModel
import java.util.*

class ViewModelTestActivity : AppCompatActivity() {

    private lateinit var mViewModel: ScoreViewModel
    private lateinit var mLiveViewModel: LiveScoreViewModel

    private lateinit var scoreViewA: TextView
    private lateinit var scoreViewB: TextView
    private lateinit var scoreViewC: TextView

    //track team A score
    var scoreTeamA: Int = 0;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_model_activity)

        scoreViewA = findViewById(R.id.team_a_score) as TextView
        displayForTeamA(scoreTeamA)

        scoreViewB = findViewById(R.id.team_b_score) as TextView
        mViewModel = ViewModelProviders.of(this).get(ScoreViewModel::class.java)
        displayForTeamB(mViewModel.scoreTeamB)


        scoreViewC = findViewById(R.id.team_c_score) as TextView
        mLiveViewModel = ViewModelProviders.of(this).get(LiveScoreViewModel::class.java)

        mLiveViewModel.scoreTeamC.observe(this, object : Observer<Int> {
            override fun onChanged(t: Int?) {
                displayForTeamC(t!!)
            }
        })

    }

    fun addOneForTeamA(v: View) {
        displayForTeamA(scoreTeamA++)
    }

    fun addOneForTeamB(v: View) {
        mViewModel.scoreTeamB = mViewModel.scoreTeamB + 1;
        displayForTeamB(mViewModel.scoreTeamB)
    }

    fun addOneForTeamC(v: View) {
        val rnd = Random()
        mLiveViewModel.scoreTeamC.value = rnd.nextInt(10);
    }


    /**
     * Resets the score for both teams back to 0.
     */
    fun resetScore(v: View) {
        scoreTeamA = 0;
        mViewModel.scoreTeamB = 0;
        mLiveViewModel.scoreTeamC.value = 0;
        displayForTeamA(scoreTeamA)
        displayForTeamB(mViewModel.scoreTeamB)

    }


    /**
     * Displays the given score for Team A.
     */
    fun displayForTeamA(score: Int) {
        scoreViewA.setText(score.toString())
    }

    /**
     * Displays the given score for Team B.
     */
    fun displayForTeamB(score: Int) {
        scoreViewB.setText(score.toString())
    }

    /**
     * Displays the given score for Team C.
     */
    fun displayForTeamC(score: Int) {
        scoreViewC.setText(score.toString())
    }

}
