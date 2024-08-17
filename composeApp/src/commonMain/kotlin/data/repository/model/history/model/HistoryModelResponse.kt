package data.repository.model.history.model

import domain.history.model.HistoryDomainModel
import kotlinx.serialization.Serializable

@Serializable
data class HistoryModelResponse(
  val id:Int?,
  val title: String?,
  val dateTime: String?,
){
  companion object {
    fun transforms(models: List<HistoryModelResponse>?): List<HistoryDomainModel> {
      return models?.map {
        transform(it)
      } ?: listOf()
    }

    fun transform(model: HistoryModelResponse): HistoryDomainModel {
      return HistoryDomainModel(
        id = model.id ?: 0,
        title = model.title ?: "",
        dateTime = model.dateTime ?: ""
      )
    }
  }
}
