package com.movie.app.common.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.movie.app.common.factoryExtensions.ViewModelFactory
import com.movie.app.mvvm.viewModel.DetailViewModel
import com.movie.app.mvvm.viewModel.MainListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * For view dependency
 * Created by hemendrag on 15/07/2019.
 */
@Module
internal abstract class ViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(MainListViewModel::class)
  internal abstract fun bindMainActivityViewModels(mainActivityViewModel: MainListViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(DetailViewModel::class)
  internal abstract fun bindMovieDetailViewModel(movieDetailViewModel:  DetailViewModel): ViewModel

  @Binds
  internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
