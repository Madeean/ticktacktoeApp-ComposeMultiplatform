package domain.history

import domain.history.model.HistoryDomainDraft
import domain.history.model.HistoryDomainModel
import kotlinx.coroutines.flow.Flow
import utils.RequestState

interface HistoryUseCase {
  fun getAllHistory(): Flow<RequestState<List<HistoryDomainModel>>>
  fun setHistory(data: HistoryDomainDraft): Flow<RequestState<HistoryDomainModel>>
}