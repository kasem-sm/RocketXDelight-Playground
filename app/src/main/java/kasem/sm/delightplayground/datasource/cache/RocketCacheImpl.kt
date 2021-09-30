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

import kasem.sm.delightplayground.Database
import kasem.sm.delightplayground.datasource.Rocket
import kasem.sm.delightplayground.datasource.RocketQueries

class RocketCacheImpl(
    db: Database
) : RocketCache {

    private val queries: RocketQueries = db.rocketQueries

    override suspend fun selectAll(): List<Rocket> {
        return queries.selectAllRockets().executeAsList()
    }

    override suspend fun selectRocketById(id: Int): Rocket {
        return queries.selectRocketById(id.toLong()).executeAsOne()
    }

    override suspend fun insertRocket(rocket: Rocket) {
        return queries.insertRocket(
            id = rocket.id,
            active = rocket.active,
            company = rocket.company,
            costPerLaunch = rocket.costPerLaunch,
            country = rocket.country,
            description = rocket.description,
            diamaterMeters = rocket.diamaterMeters,
            diameterFeet = rocket.diameterFeet,
            firstFlight = rocket.firstFlight,
            image = rocket.image,
            heightFeet = rocket.heightFeet,
            meters = rocket.meters,
            massKg = rocket.massKg,
            massLb = rocket.massLb,
            rocketId = rocket.rocketId,
            rocketTitle = rocket.rocketTitle,
            rocketType = rocket.rocketType,
            stages = rocket.stages,
            wikipedia = rocket.wikipedia
        )
    }

    override suspend fun insertRockets(rocket: List<Rocket>) {
        rocket.forEach {
            try {
                insertRocket(rocket = it)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
