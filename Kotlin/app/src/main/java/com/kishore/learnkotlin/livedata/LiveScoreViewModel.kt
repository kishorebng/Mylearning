package com.kishore.learnkotlin.livedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveScoreViewModel : ViewModel() {

    //track team C score
    val scoreTeamC = MutableLiveData<Int>()

    init {
        scoreTeamC.value = 0
    }

}