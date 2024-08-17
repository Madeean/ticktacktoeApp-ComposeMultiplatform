package presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.history.HistoryUseCase
import domain.history.model.HistoryDomainDraft
import domain.history.model.HistoryDomainModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import utils.RequestState

class HistoryViewModel(private val useCase: HistoryUseCase): ViewModel() {
  private val _allHistory: MutableStateFlow<RequestState<List<HistoryDomainModel>>> = MutableStateFlow(RequestState.Idle)
  val allHistory: StateFlow<RequestState<List<HistoryDomainModel>>> = _allHistory

  private val _history: MutableStateFlow<RequestState<HistoryDomainModel>> = MutableStateFlow(RequestState.Idle)
  val history: StateFlow<RequestState<HistoryDomainModel>> = _history

  fun getAllHistory() {
    viewModelScope.launch {
      useCase.getAllHistory().collectLatest {
        _allHistory.value = it
      }
    }
  }

  fun setHistory(data: HistoryDomainDraft) {
    viewModelScope.launch {
      useCase.setHistory(data).collectLatest {
        _history.value = it
      }
    }
  }
}