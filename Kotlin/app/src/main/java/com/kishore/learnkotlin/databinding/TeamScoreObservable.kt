package com.kishore.learnkotlin.databinding

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.kishore.learnkotlin.BR


class TeamScoreObservable : BaseObservable() {

    //track team  score
    @get:Bindable
    var observableScore: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.observableScore)
        }


}