package kasem.sm.delightplayground.datasource.cache

import com.squareup.sqldelight.ColumnAdapter
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
        /** [https://cashapp.github.io/sqldelight/android_sqlite/types/] */

        private val listOfStringsAdapter = object : ColumnAdapter<List<String>, String> {
            override fun decode(databaseValue: String) =
                if (databaseValue.isEmpty()) {
                    listOf()
                } else {
                    databaseValue.split(",")
                }

            override fun encode(value: List<String>) = value.joinToString(separator = ",")
        }

        fun build(sqlDriver: SqlDriver): RocketCache {
            return RocketCacheImpl(
                db = Database(
                    driver = sqlDriver,
                    rocketAdapter = Rocket.Adapter(
                        listOfStringsAdapter
                    )
                )
            )
        }

        val schema: SqlDriver.Schema = Database.Schema
        const val dbName: String = "rockets.db"
    }
}
