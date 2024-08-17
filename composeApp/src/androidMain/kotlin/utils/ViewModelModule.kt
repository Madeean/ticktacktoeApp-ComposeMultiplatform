package utils

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import presentation.viewmodel.HistoryViewModel
import presentation.viewmodel.TicTacToeViewModel

actual val viewModelModule = module {
  viewModelOf(::TicTacToeViewModel)
  viewModelOf(::HistoryViewModel)
}