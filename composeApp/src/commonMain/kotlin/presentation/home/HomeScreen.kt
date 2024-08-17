package presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
  navigateToTictactoeScreen: (isCreateNewGame: Boolean) -> Unit,
  navigateToHistoryScreen: () -> Unit
) {
  Scaffold(
    modifier = Modifier.fillMaxSize()
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier.padding(it).fillMaxWidth()
    ) {
      Spacer(modifier = Modifier.height(40.dp))
      Text("Tic Tac Toe App", fontWeight = FontWeight.Bold, fontSize = 20.sp )
    }
    Column(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      OutlinedButton(
        onClick = {
          navigateToTictactoeScreen(true)
        }
      ){
        Text("Create New Game")
      }
      Text("Or")
      Button(
        onClick = {
          navigateToTictactoeScreen(false)
        }
      ){
        Text("Continue Previous Game")
      }
      Spacer(modifier = Modifier.height(20.dp))
      Button(
        onClick = {
          navigateToHistoryScreen()
        }
      ){
        Text("History")
      }
    }
  }
}