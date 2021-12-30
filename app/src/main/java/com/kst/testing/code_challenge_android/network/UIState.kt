package com.kst.testing.code_challenge_android.network


sealed class UIState {
    data class Error(var exception: Throwable? = NullPointerException()) : UIState()
    data class Success(var average: Int) : UIState()
}
