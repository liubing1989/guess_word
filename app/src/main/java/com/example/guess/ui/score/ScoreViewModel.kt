package com.example.guess.ui.score

import android.util.Log
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore: Int) : ViewModel() {
    init {
        Log.i("ScoreViewModel", ":finalScore :$finalScore ")
    }
}