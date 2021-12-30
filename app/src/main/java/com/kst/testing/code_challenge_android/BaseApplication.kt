package com.kst.testing.code_challenge_android

import com.kst.testing.code_challenge_android.di.AppComponent
import com.kst.testing.code_challenge_android.di.DaggerAppComponent
import dagger.android.DaggerApplication

class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AppComponent? {
        return DaggerAppComponent.builder().application(this).build()
    }
}