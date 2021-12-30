package com.kst.testing.code_challenge_android.di

import com.kst.testing.code_challenge_android.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}