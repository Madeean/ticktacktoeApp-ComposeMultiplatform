package data.repository

import PreferencesHelper
import data.model.GameState
import domain.repository.TicTacToeRepository

class TicTacToeRepositoryImpl(private val preferencesHelper: PreferencesHelper) : TicTacToeRepository {
  private var gameState: GameState = preferencesHelper.loadGameState() ?: GameState()

  override fun getGameState(): GameState = gameState

  override fun updateGameState(state: GameState) {
    gameState = state
    preferencesHelper.saveGameState(gameState)
  }
}