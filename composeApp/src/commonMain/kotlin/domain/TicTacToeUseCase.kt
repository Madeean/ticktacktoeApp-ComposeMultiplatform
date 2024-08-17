package domain

import data.model.GameState

interface TicTacToeUseCase {
  fun getGameState(): GameState
  fun updateGameState(state: GameState)
  fun makeMoveExecute(x: Int, y: Int): GameState
  fun checkWinner(board: List<List<String>>): String?
  fun getGameOngoing(): GameState
  fun resetGameState()
}