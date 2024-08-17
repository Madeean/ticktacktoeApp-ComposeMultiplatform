package data.repository.repositoryImpl

import data.network.local.PreferencesHelper
import data.repository.model.tictactoe.model.GameState
import domain.tictactoe.TicTacToeRepository

class TicTacToeRepositoryImpl(private val preferencesHelper: PreferencesHelper) :
  TicTacToeRepository {
  private var gameState: GameState = preferencesHelper.loadGameState() ?: GameState()

  override fun getGameState(): GameState = gameState

  override fun updateGameState(state: GameState) {
    gameState = state
    preferencesHelper.saveGameState(gameState)
  }

  override fun makeMoveExecute(x: Int, y: Int): GameState {
    val currentState = gameState

    if (currentState.board[x][y].isEmpty() && !currentState.isGameOver) {
      val newBoard = currentState.board.mapIndexed { rowIndex, row ->
        row.mapIndexed { colIndex, cell ->
          if (rowIndex == x && colIndex == y) currentState.currentPlayer else cell
        }
      }

      val winner = checkWinner(newBoard)
      var isDraw = false
      if(winner == null) {
        isDraw =  newBoard.flatten().none { it.isEmpty() }
      }
      val isGameOver = winner != null || isDraw


      val newState = currentState.copy(
        board = newBoard,
        currentPlayer = if (currentState.currentPlayer == "X") "O" else "X",
        winner = winner,
        isDraw = isDraw,
        isGameOver = isGameOver
      )

      updateGameState(newState)
      return newState
    }

    return currentState
  }

  override fun checkWinner(board: List<List<String>>): String? {
    val lines = listOf(
      listOf(board[0][0], board[0][1], board[0][2]),
      listOf(board[1][0], board[1][1], board[1][2]),
      listOf(board[2][0], board[2][1], board[2][2]),
      listOf(board[0][0], board[1][0], board[2][0]),
      listOf(board[0][1], board[1][1], board[2][1]),
      listOf(board[0][2], board[1][2], board[2][2]),
      listOf(board[0][0], board[1][1], board[2][2]),
      listOf(board[0][2], board[1][1], board[2][0])
    )
    return lines.firstOrNull { it.all { cell -> cell == "X" } }?.firstOrNull()
      ?: lines.firstOrNull { it.all { cell -> cell == "O" } }?.firstOrNull()
  }

  override fun getGameOngoing(): GameState {
    return getGameState()
  }

  override fun resetGameState() {
    return updateGameState(GameState())
  }
}