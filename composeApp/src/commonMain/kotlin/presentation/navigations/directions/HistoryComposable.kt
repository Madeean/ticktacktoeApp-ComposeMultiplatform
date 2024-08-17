package presentation.navigations.directions

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import presentation.history.HistoryScreen
import presentation.utils.ConstantNavigator
import presentation.viewmodel.HistoryViewModel

fun NavGraphBuilder.HistoryComposable(
  historyViewModel: HistoryViewModel,
  navController: NavController
){
  composable(
    route = ConstantNavigator.HISTORY_SCREEN,
  ){
    HistoryScreen(
      historyViewModel,
      navController
    )
  }
}