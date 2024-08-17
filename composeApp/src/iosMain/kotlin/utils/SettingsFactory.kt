package utils

import com.russhwolf.settings.AppleSettings
import com.russhwolf.settings.Settings
import platform.Foundation.NSUserDefaults

actual object SettingsFactory {
  actual fun create(): Settings {
    val delegate = NSUserDefaults.standardUserDefaults
    return AppleSettings(delegate)
  }
}