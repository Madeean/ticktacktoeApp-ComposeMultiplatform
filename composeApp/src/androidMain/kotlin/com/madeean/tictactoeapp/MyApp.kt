package com.madeean.tictactoeapp

import KoinInitializer
import android.app.Application

class MyApp: Application() {

  override fun onCreate() {
    super.onCreate()
    KoinInitializer(applicationContext).init()
    AppContext.setUp(applicationContext)
  }
}