package presentation.navigations.directions

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import presentation.tictactoe.TicTacToeScreen
import presentation.utils.ConstantNavigator
import presentation.viewmodel.TicTacToeViewModel

fun NavGraphBuilder.TictactoeComposable(
  viewModel: TicTacToeViewModel,
  navController: NavController
){
  composable(
    route = ConstantNavigator.TICTACTOE_SCREEN,
    arguments = listOf(navArgument(ConstantNavigator.TICTACTOE_SCREEN_ARGUMENT_KEY) {
      type = NavType.BoolType
    })
  ){
    val isCreateNewGame = it.arguments?.getBoolean(ConstantNavigator.TICTACTOE_SCREEN_ARGUMENT_KEY) ?: true
    TicTacToeScreen(
      viewModel,
      isCreateNewGame,
      navController
    )
  }
}