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
