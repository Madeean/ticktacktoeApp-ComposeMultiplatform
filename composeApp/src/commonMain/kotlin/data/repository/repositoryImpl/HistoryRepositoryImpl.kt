package data.repository.repositoryImpl

import data.network.api.ApiService
import data.repository.datasource.history.HistoryDataSource
import domain.history.HistoryRepository
import domain.history.model.HistoryDomainDraft
import domain.history.model.HistoryDomainModel
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import utils.RequestState

class HistoryRepositoryImpl(
  private val httpClient: HttpClient,
  private val apiService: ApiService
): HistoryRepository {
  private val dataSource = HistoryDataSource(httpClient, apiService)

  override fun getAllHistory(): Flow<RequestState<List<HistoryDomainModel>>> {
    return flow {
      emit(RequestState.Loading)
      val data = dataSource.getAllHistory()
      emit(data)
    }
  }

  override fun setHistory(data: HistoryDomainDraft): Flow<RequestState<HistoryDomainModel>> {
    return flow {
      emit(RequestState.Loading)
      val dataResult = dataSource.setHistory(data)
      emit(dataResult)
    }
  }
}