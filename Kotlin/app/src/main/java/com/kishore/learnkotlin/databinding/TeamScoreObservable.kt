package com.kishore.learnkotlin.databinding

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable


class TeamScoreObservable : BaseObservable() {

    //track team  score
    var observableScore: Int =0
    @Bindable get() = observableScore

//    set(value) {
//        score = value
//        notifyPropertyChanged(BR.score)
//    }
}