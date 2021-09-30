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

package kasem.sm.delightplayground.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kasem.sm.delightplayground.datasource.cache.RocketCache
import kasem.sm.delightplayground.datasource.network.RocketService
import kasem.sm.delightplayground.interactors.GetRocketByIdUseCase
import kasem.sm.delightplayground.interactors.GetRocketsUseCase

@Module
@InstallIn(SingletonComponent::class)
object RocketsInteractorsModule {

    @Provides
    @Singleton
    fun provideGetRocketUseCase(
        service: RocketService,
        cache: RocketCache
    ): GetRocketsUseCase {
        return GetRocketsUseCase(
            service = service,
            cache = cache
        )
    }

    @Provides
    @Singleton
    fun provideGetRocketByIdUseCase(
        cache: RocketCache
    ): GetRocketByIdUseCase {
        return GetRocketByIdUseCase(
            cache = cache
        )
    }
}
