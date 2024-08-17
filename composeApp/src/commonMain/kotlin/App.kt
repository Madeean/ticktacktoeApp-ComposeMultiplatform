import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.compose.currentKoinScope
import presentation.navigations.SetupNavigation
import presentation.viewmodel.HistoryViewModel
import presentation.viewmodel.TicTacToeViewModel

@Composable
@Preview
fun App() {
    lateinit var navController: NavHostController
    MaterialTheme {
        KoinContext {
            val viewModel = koinViewModel<TicTacToeViewModel>()
            val historyViewModel = koinViewModel<HistoryViewModel>()
            navController = rememberNavController()
            SetupNavigation(
                navController = navController,
                ticTacToeViewModel = viewModel,
                historyViewModel = historyViewModel
            )
        }
    }
}

@Composable
inline fun <reified T : ViewModel> koinViewModel(): T {
    val scope = currentKoinScope()
    return viewModel {
        scope.get<T>()
    }
}