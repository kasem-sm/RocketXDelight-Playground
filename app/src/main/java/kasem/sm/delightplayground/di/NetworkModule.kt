package kasem.sm.delightplayground.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kasem.sm.delightplayground.datasource.network.RocketService

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRocketService(): RocketService {
        return RocketService()
    }
}
