package presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import presentation.viewmodel.TicTacToeViewModel

@Composable
fun TicTacToeApp(viewModel: TicTacToeViewModel) {
    val gameState by viewModel.gameState.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.getOngoingGame()
    }

    // Show a modal dialog if the game is over
    if (gameState.isGameOver) {
        AlertDialog(
            onDismissRequest = { /* Dismiss if needed */ },
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
            Row {
                row.forEachIndexed { y, cell ->
                    Button(onClick = { viewModel.makeMove(x, y) }, modifier = Modifier.size(100.dp)) {
                        Text(text = cell)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.resetGame() }) {
            Text(text = "Reset Game")
        }
    }
}