package domain.history.model

import kotlinx.serialization.Serializable

@Serializable
data class HistoryDomainDraft(
  val title: String,
  val dateTime: String
)