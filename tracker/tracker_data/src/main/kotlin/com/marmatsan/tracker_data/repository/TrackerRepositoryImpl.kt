package com.marmatsan.tracker_data.repository

import com.marmatsan.tracker_data.local.TrackerDao
import com.marmatsan.tracker_data.mapper.toTrackableFood
import com.marmatsan.tracker_data.mapper.toTrackedFood
import com.marmatsan.tracker_data.remote.api.OpenFoodApi
import com.marmatsan.tracker_domain.models.TrackableFood
import com.marmatsan.tracker_domain.models.TrackedFood
import com.marmatsan.tracker_domain.repository.RequestState
import com.marmatsan.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class TrackerRepositoryImpl(
    private val dao: TrackerDao,
    private val api: OpenFoodApi
) : TrackerRepository {

    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Flow<RequestState<List<TrackableFood>>> {
        return flow {
            emit(RequestState.Loading(isLoading = true))

            val remoteProducts = try {
                val response = api.searchFood(
                    query = query,
                    page = page,
                    pageSize = pageSize
                )
                response.products.mapNotNull { it.toTrackableFood() }
            } catch (e: Exception) { // TODO: Ktor error handling
                emit(RequestState.Error(message = e.message.orEmpty()))
                null
            }

            emit(RequestState.Success(remoteProducts))

        }
    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        TODO("Not yet implemented")
    }

    override fun getFoodsFromCurrentDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return dao.getFoodsFromCurrentDate(
            day = localDate.dayOfMonth,
            month = localDate.monthValue,
            year = localDate.year
        ).map { trackedFoodEntities ->
            trackedFoodEntities.map { it.toTrackedFood() }
        }
    }

}