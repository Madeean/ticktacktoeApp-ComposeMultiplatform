package domain.history

import domain.history.model.HistoryDomainDraft
import domain.history.model.HistoryDomainModel
import kotlinx.coroutines.flow.Flow
import utils.RequestState

class HistoryUseCaseImpl(private val repository: HistoryRepository): HistoryUseCase {
  override fun getAllHistory(): Flow<RequestState<List<HistoryDomainModel>>> {
    return repository.getAllHistory()
  }

  override fun setHistory(data: HistoryDomainDraft): Flow<RequestState<HistoryDomainModel>> {
    return repository.setHistory(data)
  }
}