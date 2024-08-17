package data.network.local

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import data.model.GameState
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class PreferencesHelper(private val settings: Settings) {

  private val gameStateKey = "game_state_key"

  fun saveGameState(gameState: GameState) {
    val jsonString = Json.encodeToString(gameState)
    settings[gameStateKey] = jsonString
  }

  fun loadGameState(): GameState? {
    val jsonString = settings.getStringOrNull(gameStateKey)
    return jsonString?.let { Json.decodeFromString<GameState>(it) }
  }

  fun clearGameState() {
    settings.remove(gameStateKey)
  }
}