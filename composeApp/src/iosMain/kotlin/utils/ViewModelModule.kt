package utils

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import presentation.viewmodel.TicTacToeViewModel
import presentation.viewmodel.HistoryViewModel

actual val viewModelModule = module {
  singleOf(::TicTacToeViewModel)
  singleOf(::HistoryViewModel)
}