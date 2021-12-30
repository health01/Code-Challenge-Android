package com.kst.testing.code_challenge_android.di.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kst.testing.code_challenge_android.viewModels.ViewModelProviderFactory
import com.kst.testing.code_challenge_android.ui.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindFirstFragmentViewModel(viewModel: MainViewModel): ViewModel
}