package data.repository.datasource.history

import data.network.api.ApiService
import data.repository.model.history.model.HistoryModelResponse
import domain.history.model.HistoryDomainDraft
import domain.history.model.HistoryDomainModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import utils.RequestState

class HistoryDataSource(
  private val httpClient: HttpClient,
  private val apiService: ApiService
) {
  suspend fun getAllHistory(): RequestState<List<HistoryDomainModel>> {
    return try {
      val response = httpClient.get("${apiService.BASE_URL_HISTORIES}")

      if (response.status.value == 200) {
        val apiResponse = response.body<List<HistoryModelResponse>>()
        val data = HistoryModelResponse.transforms(apiResponse)
        RequestState.Success(data)
      } else {
        val error = Throwable(message = "HTTP ERROR ${response.status.value}")
        RequestState.Error(error)
      }
    } catch (e: Exception) {
      RequestState.Error(e)
    }
  }

  suspend fun setHistory(dataBody: HistoryDomainDraft): RequestState<HistoryDomainModel> {
    return try {
      val response = httpClient.post("${apiService.BASE_URL_HISTORY}"){
        contentType(ContentType.Application.Json)
        setBody(HistoryDomainDraft(
          dateTime = dataBody.dateTime,
          title = dataBody.title
        ))
      }

      if (response.status.value == 200) {
        val apiResponse = response.body<HistoryModelResponse>()
        val data = HistoryModelResponse.transform(apiResponse)
        RequestState.Success(data)
      } else {
        val error = Throwable(message = "HTTP ERROR ${response.status.value}")
        RequestState.Error(error)
      }
    } catch (e: Exception) {
      RequestState.Error(e)
    }
  }
}