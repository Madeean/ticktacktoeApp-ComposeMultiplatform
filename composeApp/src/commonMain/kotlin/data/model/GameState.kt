package data.model

import kotlinx.serialization.Serializable

@Serializable
data class GameState(
  val board: List<List<String>> = List(3) { List(3) { "" } },
  val currentPlayer: String = "X",
  val winner: String? = null,
  val isDraw: Boolean = false,
  val isGameOver: Boolean = false
)