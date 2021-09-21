package kasem.sm.delightplayground.di

import android.app.Application
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kasem.sm.delightplayground.datasource.cache.RocketCache

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Provides
    @Singleton
    fun provideAndroidDriver(app: Application): SqlDriver {
        return AndroidSqliteDriver(
            schema = RocketCache.schema,
            context = app,
            name = RocketCache.dbName
        )
    }

    @Provides
    @Singleton
    fun provideRocketCache(
        sqlDriver: SqlDriver
    ): RocketCache {
        return RocketCache.build(sqlDriver = sqlDriver)
    }
}
