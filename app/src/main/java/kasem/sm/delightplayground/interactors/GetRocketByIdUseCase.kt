package kasem.sm.delightplayground.interactors

import kasem.sm.delightplayground.core.state.ProgressState
import kasem.sm.delightplayground.core.state.Resource
import kasem.sm.delightplayground.datasource.Rocket
import kasem.sm.delightplayground.datasource.cache.RocketCache
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRocketByIdUseCase(
    private val cache: RocketCache
) {

    operator fun invoke(rocketId: Int): Flow<Resource<Rocket>> = flow {
        try {
            emit(Resource.Loading<Rocket>(progressState = ProgressState.Progress))

            // Request cache
            val rocket = try {
                cache.selectRocketById(id = rocketId)
            } catch (e: Exception) {

                /**
                 * In our case, there would be 0.1% chance that the rocket would be null,
                 * in production app you should handle null case by retrieving single rocket from the API
                 * */

                emit(Resource.Error(errorMessage = e.message ?: "Rocket not found!"))
                null
            }

            rocket?.let {
                emit(Resource.Success(data = it))
            }
        } catch (e: Exception) {
            emit(Resource.Error<Rocket>(errorMessage = e.message ?: "Unknown Error"))
        } finally {
            emit(Resource.Loading<Rocket>(progressState = ProgressState.Idle))
        }
    }
}
