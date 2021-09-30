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
