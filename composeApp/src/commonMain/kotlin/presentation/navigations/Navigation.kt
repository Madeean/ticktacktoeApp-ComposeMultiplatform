package presentation.navigations

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import presentation.navigations.directions.HomeComposable
import presentation.navigations.directions.TictactoeComposable
import presentation.utils.ConstantNavigator
import presentation.viewmodel.TicTacToeViewModel

@Composable
fun SetupNavigation(
  navController: NavHostController,
  ticTacToeViewModel: TicTacToeViewModel
) {
  val screen = remember(navController) {
    Screens(navController = navController)
  }

  NavHost(
    navController = navController,
    startDestination = ConstantNavigator.HOME_SCREEN,
  ) {
    HomeComposable(
      navigateToTictactoeScreen = screen.tictactoeScreen
    )
    TictactoeComposable(viewModel = ticTacToeViewModel, navController)
  }
}