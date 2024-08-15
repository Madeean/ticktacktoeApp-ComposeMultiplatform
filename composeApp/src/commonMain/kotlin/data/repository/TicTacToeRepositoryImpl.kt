package data.repository

import data.model.GameState
import domain.repository.TicTacToeRepository

class TicTacToeRepositoryImpl : TicTacToeRepository {
  private var gameState = GameState()

  override fun getGameState(): GameState = gameState

  override fun updateGameState(state: GameState) {
    gameState = state
  }
}