package presentation.navigations

import androidx.navigation.NavHostController
import presentation.utils.ConstantNavigator

class Screens(navController: NavHostController) {

  val tictactoeScreen: (isCreateNewGame: Boolean) -> Unit = {
    navController.navigate(route = "tictactoe_screen/$it")
  }

  val historyScreen: () -> Unit = {
    navController.navigate(route = ConstantNavigator.HISTORY_SCREEN)
  }
}