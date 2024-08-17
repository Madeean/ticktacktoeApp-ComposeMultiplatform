package di

import data.network.local.PreferencesHelper
import utils.SettingsFactory
import com.russhwolf.settings.Settings
import data.repository.TicTacToeRepositoryImpl
import domain.TicTacToeRepository
import domain.TicTacToeUseCase
import domain.TicTacToeUseCaseImpl
import org.koin.dsl.module
import presentation.viewmodel.TicTacToeViewModel

val appModule = module {

  single<Settings> { SettingsFactory.create() }
  single { PreferencesHelper(get()) }
  single<TicTacToeRepository> { TicTacToeRepositoryImpl(get()) }
  single<TicTacToeUseCase> {TicTacToeUseCaseImpl(get())}
  single { TicTacToeViewModel(get()) }
}