package domain.usecase

import data.model.GameState
import domain.repository.TicTacToeRepository

class ResetGameUseCase(private val repository: TicTacToeRepository) {
  fun execute() {
    repository.updateGameState(GameState())
  }
}