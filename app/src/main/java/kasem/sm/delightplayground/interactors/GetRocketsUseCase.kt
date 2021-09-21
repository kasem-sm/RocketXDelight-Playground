package kasem.sm.delightplayground.interactors

import kasem.sm.delightplayground.core.ProgressState
import kasem.sm.delightplayground.core.Resource
import kasem.sm.delightplayground.datasource.Rocket
import kasem.sm.delightplayground.datasource.cache.RocketCache
import kasem.sm.delightplayground.datasource.cache.util.toDbList
import kasem.sm.delightplayground.datasource.network.RocketService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRocketsUseCase(
    private val service: RocketService,
    private val cache: RocketCache
) {

    operator fun invoke(): Flow<Resource<List<Rocket>>> = flow {
        try {
            emit(Resource.Loading<List<Rocket>>(progressState = ProgressState.Progress))

            // TODO: 9/20/2021 Remove
            delay(500)

            // Get Request
            val rockets = try {
                service.getRockets()
            } catch (e: Exception) {
                emit(Resource.Error(errorMessage = e.message ?: "Unknown Network Error"))
                listOf()
            }

            /** Map [kasem.sm.delightplayground.datasource.network.model.RocketDtoItem] to [Rocket]*/
            cache.insertRockets(rocket = toDbList(rockets))

            // Get from cache & emit
            val cachedRockets = cache.selectAll()
            emit(Resource.Success(data = cachedRockets))
        } catch (e: Exception) {
            emit(Resource.Error<List<Rocket>>(errorMessage = e.message ?: "Unknown Error"))
        } finally {
            emit(Resource.Loading<List<Rocket>>(progressState = ProgressState.Idle))
        }
    }
}
