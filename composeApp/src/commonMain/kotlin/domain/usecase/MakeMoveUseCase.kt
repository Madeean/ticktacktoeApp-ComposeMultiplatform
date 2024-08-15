package domain.usecase

import data.model.GameState
import domain.repository.TicTacToeRepository

class MakeMoveUseCase(private val repository: TicTacToeRepository) {

  fun execute(x: Int, y: Int): GameState {
    val currentState = repository.getGameState()

    if (currentState.board[x][y].isEmpty() && !currentState.isGameOver) {
      val newBoard = currentState.board.mapIndexed { rowIndex, row ->
        row.mapIndexed { colIndex, cell ->
          if (rowIndex == x && colIndex == y) currentState.currentPlayer else cell
        }
      }

      val winner = checkWinner(newBoard)
      val isDraw = newBoard.flatten().none { it.isEmpty() }
      val isGameOver = winner != null || isDraw

      val newState = currentState.copy(
        board = newBoard,
        currentPlayer = if (currentState.currentPlayer == "X") "O" else "X",
        winner = winner,
        isDraw = isDraw,
        isGameOver = isGameOver
      )

      repository.updateGameState(newState)
      return newState
    }

    return currentState
  }

  private fun checkWinner(board: List<List<String>>): String? {
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
}