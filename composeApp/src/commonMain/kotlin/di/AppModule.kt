package di

import data.repository.TicTacToeRepositoryImpl
import domain.repository.TicTacToeRepository
import domain.usecase.MakeMoveUseCase
import domain.usecase.ResetGameUseCase
import org.koin.dsl.module
import presentation.viewmodel.TicTacToeViewModel

val appModule = module {

  single<TicTacToeRepository> { TicTacToeRepositoryImpl() }
  factory { MakeMoveUseCase(get()) }
  factory { ResetGameUseCase(get()) }
  factory { TicTacToeViewModel(get(), get()) }
}