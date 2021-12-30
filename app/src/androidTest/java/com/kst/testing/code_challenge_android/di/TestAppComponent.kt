package com.kst.testing.code_challenge_android.di

import com.kst.testing.code_challenge_android.TestBaseApplication
import com.kst.testing.code_challenge_android.di.viewModel.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBuildersModule::class,
        ViewModelFactoryModule::class,
        TestAppModule::class,
    ]
)
interface TestAppComponent : AndroidInjector<TestBaseApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: TestBaseApplication): Builder
        fun build(): TestAppComponent
    }
}