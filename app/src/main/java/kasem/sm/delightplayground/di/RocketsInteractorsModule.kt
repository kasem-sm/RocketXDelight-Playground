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
