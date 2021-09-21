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
