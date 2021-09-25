package kasem.sm.delightplayground.datasource.cache.util

import kasem.sm.delightplayground.datasource.Rocket
import kasem.sm.delightplayground.datasource.network.model.RocketDtoItem

fun RocketDtoItem.toDbRocket(): Rocket {
    return Rocket(
        id = id.toLong(),
        active = if (active) 1 else 0,
        company = company,
        costPerLaunch = costPerLaunch.toLong(),
        country = country,
        description = description,
        diamaterMeters = diameter.meters.toString(),
        diameterFeet = diameter.feet.toString(),
        firstFlight = firstFlight,
        image = imagesList,
        heightFeet = height.feet.toString(),
        meters = height.meters.toString(),
        massKg = mass.kg.toLong(),
        massLb = mass.lb.toLong(),
        rocketId = rocketId,
        rocketTitle = rocketName,
        rocketType = rocketType,
        stages = stages.toLong(),
        wikipedia = wikipedia
    )
}

fun toDbList(initial: List<RocketDtoItem>): List<Rocket> {
    return initial.map {
        it.toDbRocket()
    }
}
