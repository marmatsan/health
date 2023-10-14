package com.marmatsan.tracker_domain.usecases

import com.marmatsan.tracker_domain.models.TrackedFood
import com.marmatsan.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class GetFoodsFromCurrentDate(
    private val repository: TrackerRepository
) {
    operator fun invoke(date: LocalDate): Flow<List<TrackedFood>> {
        return repository.getFoodsFromCurrentDate(date)
    }
}