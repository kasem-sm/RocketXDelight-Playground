/*
 Copyright (c) 2021 Kasem S.M.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

package kasem.sm.delightplayground.interactors

import kasem.sm.delightplayground.core.domain.ProgressState
import kasem.sm.delightplayground.core.domain.Resource
import kasem.sm.delightplayground.datasource.Rocket
import kasem.sm.delightplayground.datasource.cache.RocketCache
import kasem.sm.delightplayground.datasource.cache.util.toDbList
import kasem.sm.delightplayground.datasource.network.RocketService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRocketsUseCase(
    private val service: RocketService,
    private val cache: RocketCache
) {

    operator fun invoke(): Flow<Resource<List<Rocket>>> = flow {
        try {
            emit(Resource.Loading<List<Rocket>>(progressState = ProgressState.Progress))

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
