package com.kishore.learnkotlin.databinding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveTeamScore : ViewModel() {

    //track team C score
    val teamScore = MutableLiveData<Int>()

    init {
        teamScore.value = 0
    }
}