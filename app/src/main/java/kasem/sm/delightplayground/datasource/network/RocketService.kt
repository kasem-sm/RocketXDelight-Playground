package kasem.sm.delightplayground.datasource.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kasem.sm.delightplayground.datasource.network.model.RocketDtoItem
import kotlinx.serialization.json.Json

interface RocketService {

    suspend fun getRockets(): List<RocketDtoItem>

    companion object Builder {

        operator fun invoke(): RocketService {
            return RocketServiceImpl(
                httpClient = HttpClient(Android) {
                    install(JsonFeature) {
                        serializer = KotlinxSerializer(
                            json = Json {
                                ignoreUnknownKeys = true
                            }
                        )
                    }
                }
            )
        }
    }
}
