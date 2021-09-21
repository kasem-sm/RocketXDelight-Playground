package kasem.sm.delightplayground.datasource.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import kasem.sm.delightplayground.datasource.network.model.RocketDtoItem

class RocketServiceImpl(
    private val httpClient: HttpClient
) : RocketService {
    override suspend fun getRockets(): List<RocketDtoItem> {
        return httpClient.get {
            url(END_POINT_ROCKETS)
        }
    }

    companion object {
        private const val BASE_URL = "https://api.spacexdata.com/"
        private const val END_POINT_ROCKETS = "${BASE_URL}v3/rockets"
    }
}
