package presentation.viewmodel

import androidx.lifecycle.ViewModel
import data.model.GameState
import domain.TicTacToeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TicTacToeViewModel(
  private val useCase: TicTacToeUseCase
) : ViewModel() {

  private val _gameState = MutableStateFlow(GameState())
  val gameState: StateFlow<GameState> get() = _gameState

  fun makeMove(x: Int, y: Int) {
    _gameState.value = useCase.makeMoveExecute(x, y)
  }

  fun resetGame() {
    useCase.resetGameState()
    _gameState.value = GameState()
  }

  fun getOngoingGame() {
    _gameState.value = useCase.getGameOngoing()
  }
}