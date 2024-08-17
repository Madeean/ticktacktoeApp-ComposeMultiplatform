package di

import data.network.local.PreferencesHelper
import utils.SettingsFactory
import com.russhwolf.settings.Settings
import data.repository.TicTacToeRepositoryImpl
import domain.repository.TicTacToeRepository
import domain.usecase.MakeMoveUseCase
import domain.usecase.ResetGameUseCase
import org.koin.dsl.module
import presentation.viewmodel.TicTacToeViewModel

val appModule = module {

  single<Settings> { SettingsFactory.create() }
  single { PreferencesHelper(get()) }
  single<TicTacToeRepository> { TicTacToeRepositoryImpl(get()) }
  factory { MakeMoveUseCase(get()) }
  factory { ResetGameUseCase(get()) }
  single { TicTacToeViewModel(get(), get()) }
}