package presentation.viewmodel

import androidx.lifecycle.ViewModel
import data.model.GameState
import domain.usecase.MakeMoveUseCase
import domain.usecase.ResetGameUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TicTacToeViewModel(
  private val makeMoveUseCase: MakeMoveUseCase,
  private val resetGameUseCase: ResetGameUseCase
) : ViewModel() {

  private val _gameState = MutableStateFlow(GameState())
  val gameState: StateFlow<GameState> get() = _gameState

  fun makeMove(x: Int, y: Int) {
    _gameState.value = makeMoveUseCase.execute(x, y)
  }

  fun resetGame() {
    resetGameUseCase.execute()
    _gameState.value = GameState()
  }

  fun getOngoingGame() {
    _gameState.value = makeMoveUseCase.getGameOngoing()
  }
}