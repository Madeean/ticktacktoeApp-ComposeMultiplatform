package presentation.navigations.directions

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import presentation.home.HomeScreen
import presentation.utils.ConstantNavigator

fun NavGraphBuilder.HomeComposable(
  navigateToTictactoeScreen: (isCreateNewGame: Boolean) -> Unit
){
  composable(
    route = ConstantNavigator.HOME_SCREEN,
  ){
    HomeScreen(
      navigateToTictactoeScreen
    )
  }
}