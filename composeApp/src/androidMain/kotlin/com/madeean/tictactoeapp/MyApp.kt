package com.madeean.tictactoeapp

import utils.KoinInitializer
import android.app.Application
import utils.AppContext

class MyApp: Application() {

  override fun onCreate() {
    super.onCreate()
    KoinInitializer(applicationContext).init()
    AppContext.setUp(applicationContext)
  }
}