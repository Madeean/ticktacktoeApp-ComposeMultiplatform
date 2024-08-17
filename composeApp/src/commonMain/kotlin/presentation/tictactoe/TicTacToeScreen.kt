package presentation.tictactoe

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import presentation.viewmodel.TicTacToeViewModel

@Composable
fun TicTacToeScreen (viewModel: TicTacToeViewModel, isCreateNewGame: Boolean, navController: NavController) {
  val gameState by viewModel.gameState.collectAsState()

  LaunchedEffect(key1 = true) {
    if(isCreateNewGame) viewModel.resetGame() else viewModel.getOngoingGame()
  }

  if (gameState.isGameOver) {
    AlertDialog(
      onDismissRequest = { },
      title = {
        Text(text = if (gameState.isDraw) "It's a Draw!" else "${gameState.winner} Wins!")
      },
      confirmButton = {
        Button(onClick = { viewModel.resetGame() }) {
          Text("Restart Game")
        }
      }
    )
  }

  Column(
  horizontalAlignment = Alignment.CenterHorizontally,
  verticalArrangement = Arrangement.Center,
  modifier = Modifier.fillMaxSize()
  ) {
    Text(text = "Player ${if (gameState.currentPlayer == "X") "1" else "2"}'s Turn")

    Spacer(modifier = Modifier.height(16.dp))

    gameState.board.forEachIndexed { x, row ->
      Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        row.forEachIndexed { y, cell ->
          OutlinedButton(onClick = { viewModel.makeMove(x, y) }, modifier = Modifier.size(100.dp).padding(5.dp)) {
            Text(text = cell)
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Row(
      modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      OutlinedButton(onClick = { navController.navigateUp()}) {
        Text(text = "Back")
      }
      Button(onClick = { viewModel.resetGame() }) {
        Text(text = "Reset Game")
      }
    }
  }
}