package di

import data.network.local.PreferencesHelper
import utils.SettingsFactory
import com.russhwolf.settings.Settings
import data.network.api.ApiService
import data.network.api.httpClient
import data.repository.repositoryImpl.HistoryRepositoryImpl
import data.repository.repositoryImpl.TicTacToeRepositoryImpl
import domain.history.HistoryRepository
import domain.history.HistoryUseCase
import domain.history.HistoryUseCaseImpl
import domain.tictactoe.TicTacToeRepository
import domain.tictactoe.TicTacToeUseCase
import domain.tictactoe.TicTacToeUseCaseImpl
import org.koin.dsl.module
import presentation.viewmodel.HistoryViewModel
import presentation.viewmodel.TicTacToeViewModel

val appModule = module {
  single { httpClient }
  single { ApiService() }

  single<Settings> { SettingsFactory.create() }
  single { PreferencesHelper(get()) }
  single<TicTacToeRepository> { TicTacToeRepositoryImpl(get()) }
  single<TicTacToeUseCase> { TicTacToeUseCaseImpl(get()) }
  single { TicTacToeViewModel(get()) }

  single<HistoryRepository> { HistoryRepositoryImpl(get(), get()) }
  single<HistoryUseCase> { HistoryUseCaseImpl(get()) }
  factory { HistoryViewModel(get()) }
}