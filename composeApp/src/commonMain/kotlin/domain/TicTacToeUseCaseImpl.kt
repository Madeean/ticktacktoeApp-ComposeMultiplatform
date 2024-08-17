package domain

import data.model.GameState

class TicTacToeUseCaseImpl(private val repository: TicTacToeRepository): TicTacToeUseCase {
  override fun getGameState(): GameState {
    return repository.getGameState()
  }

  override fun updateGameState(state: GameState) {
    return repository.updateGameState(state)
  }

  override fun makeMoveExecute(x: Int, y: Int): GameState {
    return repository.makeMoveExecute(x, y)
  }

  override fun checkWinner(board: List<List<String>>): String? {
    return repository.checkWinner(board)
  }

  override fun getGameOngoing(): GameState {
    return repository.getGameOngoing()
  }

  override fun resetGameState() {
    return repository.resetGameState()
  }
}