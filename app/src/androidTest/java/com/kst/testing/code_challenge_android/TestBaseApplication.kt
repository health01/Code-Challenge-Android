package com.kst.testing.code_challenge_android

import com.kst.testing.code_challenge_android.di.DaggerTestAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

open class TestBaseApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {
        return DaggerTestAppComponent.builder().application(this).build()
    }
}