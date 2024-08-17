import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController(
  configure = {
    utils.KoinInitializer().init()
  }
) {
  App()
}