package data.network.api

class ApiService {
  private val END_POINT = "http://192.168.0.141:8080/"

  private val HISTORY = "history"
  val BASE_URL_HISTORY = "$END_POINT$HISTORY"

  private val HISTORIES = "histories"
  val BASE_URL_HISTORIES = "$END_POINT$HISTORIES"
}