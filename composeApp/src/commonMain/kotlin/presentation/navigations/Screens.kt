package presentation.navigations

import androidx.navigation.NavHostController

class Screens(navController: NavHostController) {

  val tictactoeScreen: (isCreateNewGame: Boolean) -> Unit = {
    navController.navigate(route = "tictactoe_screen/$it")
  }
}