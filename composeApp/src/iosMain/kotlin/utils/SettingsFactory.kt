package utils

import com.russhwolf.settings.AppleSettings
import com.russhwolf.settings.Settings

actual object SettingsFactory {
  actual fun create(): Settings {
    val delegate = NSUserDefaults.standardUserDefaults
    return AppleSettings(delegate)
  }
}