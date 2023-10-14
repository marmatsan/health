package com.marmatsan.tracker_domain.usecases

import com.marmatsan.tracker_domain.models.TrackableFood
import com.marmatsan.tracker_domain.repository.RequestState
import com.marmatsan.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow

class SearchFood(
    private val repository: TrackerRepository
) {
    suspend operator fun invoke(
        query: String,
        page: Int = 1,
        pageSize: Int = 40
    ): Flow<RequestState<List<TrackableFood>>> {
        return repository
            .searchFood(
                query = query,
                page = page,
                pageSize = pageSize
            )
    }
}