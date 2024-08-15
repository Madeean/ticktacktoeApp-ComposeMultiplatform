package domain.repository

import data.model.GameState

interface TicTacToeRepository {
  fun getGameState(): GameState
  fun updateGameState(state: GameState)
}