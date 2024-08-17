package utils

import android.preference.PreferenceManager
import com.russhwolf.settings.AndroidSettings
import com.russhwolf.settings.Settings

actual object SettingsFactory {

  actual fun create(): Settings {
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AppContext.get())
    return AndroidSettings(sharedPreferences)
  }
}