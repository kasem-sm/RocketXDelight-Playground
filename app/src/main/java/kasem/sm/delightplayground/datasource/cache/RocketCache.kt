package kasem.sm.delightplayground.datasource.cache

import com.squareup.sqldelight.db.SqlDriver
import kasem.sm.delightplayground.Database
import kasem.sm.delightplayground.datasource.Rocket

interface RocketCache {

    suspend fun selectAll(): List<Rocket>

    suspend fun selectRocketById(id: Int): Rocket?

    suspend fun insertRocket(
        rocket: Rocket
    )

    suspend fun insertRockets(
        rocket: List<Rocket>
    )

    companion object Factory {
        fun build(sqlDriver: SqlDriver): RocketCache {
            return RocketCacheImpl(
                db = Database(sqlDriver)
            )
        }

        val schema: SqlDriver.Schema = Database.Schema
        const val dbName: String = "rockets.db"
    }
}
