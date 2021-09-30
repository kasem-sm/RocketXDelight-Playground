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
import kasem.sm.delightplayground.core.util.serializeData
import kasem.sm.delightplayground.datasource.Rocket
import kasem.sm.delightplayground.datasource.cache.RocketCacheFake
import kasem.sm.delightplayground.datasource.cache.RocketDatabaseFake
import kasem.sm.delightplayground.datasource.network.RocketServiceFake
import kasem.sm.delightplayground.datasource.network.data.RocketDataValid
import kasem.sm.delightplayground.datasource.network.data.RocketDataValid.TOTAL_ROCKET
import kasem.sm.delightplayground.datasource.network.response.RocketResponseType
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * Inspired by https://github.com/mitchtabian/Dota-Info/blob/master/hero/hero-interactors/src/test/java/com/codingwithmitch/hero_interactors/GetHerosTest.kt
 */

class GetRocketsUseCaseTest {

    /**
     * Test Get rockets Success
     * */
    @Test
    fun getRockets_Success_from_network() = runBlocking {

        val rocketsDatabase = RocketDatabaseFake()
        val rocketCache = RocketCacheFake(rocketsDatabase)
        val rocketService = RocketServiceFake.build(
            type = RocketResponseType.VALID_DATA // good data
        )

        val getRocketsUseCase = GetRocketsUseCase(
            service = rocketService,
            cache = rocketCache
        )

        // Assert that the cache was empty
        val cache = rocketCache.selectAll()
        assert(cache.isEmpty())

        val emissions = getRocketsUseCase.invoke().toList()

        //  The First emission should always be loading
        assert(emissions[0] == Resource.Loading<List<Rocket>>(ProgressState.Progress))

        // As we passed Valid data, the result was Success
        assert(emissions[1] is Resource.Success)
        assert((emissions[1] as Resource.Success).data?.size == TOTAL_ROCKET)

        // Check if the cache is not empty anymore
        val cachedRockets = rocketCache.selectAll()
        assert(cachedRockets.isNotEmpty())
        assert(cachedRockets.size == TOTAL_ROCKET)

        // At fourth emission, progress state would be idle
        assert(emissions[2] == Resource.Loading<List<Rocket>>(ProgressState.Idle))
    }

    /**
     * Test Get rockets Failed from network but Success from cache
     */
    @Test
    fun getRockets_Failed_but_Success_from_cache() = runBlocking {

        val rocketsDatabase = RocketDatabaseFake()
        val rocketCache = RocketCacheFake(rocketsDatabase)
        val rocketService = RocketServiceFake.build(
            type = RocketResponseType.INVALID_DATA // bad data
        )

        val getRocketsUseCase = GetRocketsUseCase(
            service = rocketService,
            cache = rocketCache
        )

        // Confirm that the cache is empty initially
        var cache = rocketCache.selectAll()
        assert(cache.isEmpty())

        // Add some valid data to cache
        val validData = RocketDataValid.data.serializeData()
        rocketCache.insertRockets(validData)

        // Check that the data has been inserted into the cache successfully!
        cache = rocketCache.selectAll()
        assert(cache.isNotEmpty())

        val emissions = getRocketsUseCase.invoke().toList()

        // The First emission should always be loading
        assert(emissions[0] == Resource.Loading<List<Rocket>>(ProgressState.Progress))

        // Second emission would be error as we passed Invalid Data
        assert(emissions[1] is Resource.Error)
        (emissions[1] as Resource.Error).errorMessage?.let { msg ->
            assert(msg.contains("Unexpected JSON token at offset"))
        }

        // Third emission would still be success as the cache was not empty
        assert(emissions[2] is Resource.Success)
        assert((emissions[2] as Resource.Success).data?.size == TOTAL_ROCKET)

        // At fourth emission, progress state would be idle
        assert(emissions[3] == Resource.Loading<List<Rocket>>(ProgressState.Idle))
    }

    /**
     * Test Get rockets Failed from network
     */
    @Test
    fun getRockets_Failed() = runBlocking {

        val rocketsDatabase = RocketDatabaseFake()
        val rocketCache = RocketCacheFake(rocketsDatabase)
        val rocketService = RocketServiceFake.build(
            type = RocketResponseType.INVALID_DATA // bad data
        )

        val getRocketsUseCase = GetRocketsUseCase(
            service = rocketService,
            cache = rocketCache
        )

        // Confirm that the cache is empty initially
        var cache = rocketCache.selectAll()
        assert(cache.isEmpty())

        val emissions = getRocketsUseCase.invoke().toList()

        // The First emission should always be loading
        assert(emissions[0] == Resource.Loading<List<Rocket>>(ProgressState.Progress))

        // Second emission would be error as we passed Invalid Data
        assert(emissions[1] is Resource.Error)
        (emissions[1] as Resource.Error).errorMessage?.let { msg ->
            assert(msg.contains("Unexpected JSON token at offset"))
        }

        // Third emission would still be success as in case of an error, we passed an empty list to data
        assert(emissions[2] is Resource.Success)
        assert((emissions[2] as Resource.Success).data?.size == 0)

        // Confirm that the cache is still empty
        cache = rocketCache.selectAll()
        assert(cache.isEmpty())

        // At fourth emission, progress state would be idle
        assert(emissions[3] == Resource.Loading<List<Rocket>>(ProgressState.Idle))
    }

    /**
     * Test Get rockets is empty
     */
    @Test
    fun getRockets_Success_but_is_Empty() = runBlocking {

        val rocketsDatabase = RocketDatabaseFake()
        val rocketCache = RocketCacheFake(rocketsDatabase)
        val rocketService = RocketServiceFake.build(
            type = RocketResponseType.EMPTY_DATA // bad data
        )

        val getRocketsUseCase = GetRocketsUseCase(
            service = rocketService,
            cache = rocketCache
        )

        // Confirm that the cache is empty initially
        var cache = rocketCache.selectAll()
        assert(cache.isEmpty())

        val emissions = getRocketsUseCase.invoke().toList()

        // The First emission should always be loading
        assert(emissions[0] == Resource.Loading<List<Rocket>>(ProgressState.Progress))

        // Second emission would be success but the data size would be 0
        assert(emissions[1] is Resource.Success)
        assert((emissions[1] as Resource.Success).data?.size == 0)

        // Confirm that the cache is still empty
        cache = rocketCache.selectAll()
        assert(cache.isEmpty())

        // At third emission, progress state would be idle
        assert(emissions[2] == Resource.Loading<List<Rocket>>(ProgressState.Idle))
    }
}
