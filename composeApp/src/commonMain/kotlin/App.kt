import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.compose.currentKoinScope
import presentation.ui.TicTacToeApp
import presentation.viewmodel.TicTacToeViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        KoinContext {
            val viewModel = koinViewModel<TicTacToeViewModel>()
            TicTacToeApp(viewModel)
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