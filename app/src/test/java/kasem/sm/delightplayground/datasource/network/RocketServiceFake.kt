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

package kasem.sm.delightplayground.datasource.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.http.HttpStatusCode
import io.ktor.http.Url
import io.ktor.http.fullPath
import io.ktor.http.headersOf
import io.ktor.http.hostWithPort
import kasem.sm.delightplayground.datasource.network.data.RocketDataEmpty
import kasem.sm.delightplayground.datasource.network.data.RocketDataInvalid
import kasem.sm.delightplayground.datasource.network.data.RocketDataValid
import kasem.sm.delightplayground.datasource.network.response.RocketResponseType

class RocketServiceFake {

    /**
     * Building fake Ktor-Client
     * https://ktor.io/docs/http-client-testing.html#test-client
     */

    companion object {

        private val Url.hostWithPortIfRequired: String get() = if (port == protocol.defaultPort) host else hostWithPort
        private val Url.fullUrl: String get() = "${protocol.name}://$hostWithPortIfRequired$fullPath"

        fun build(
            type: RocketResponseType
        ): RocketService {
            val client = HttpClient(MockEngine) {
                install(JsonFeature) {
                    serializer = KotlinxSerializer(
                        json = kotlinx.serialization.json.Json {
                            ignoreUnknownKeys = true
                        }
                    )
                }
                engine {
                    addHandler { request ->
                        if (request.url.fullUrl == RocketServiceImpl.END_POINT_ROCKETS) {
                            val responseHeaders = headersOf(
                                "Content-Type" to listOf("application/json", "charset=utf-8")
                            )
                            when (type) {
                                /** define what the response should be based on which [type] is passed
                                 */
                                RocketResponseType.EMPTY_DATA -> {
                                    respond(
                                        RocketDataEmpty.data,
                                        status = HttpStatusCode.OK,
                                        headers = responseHeaders
                                    )
                                }
                                RocketResponseType.INVALID_DATA -> {
                                    respond(
                                        RocketDataInvalid.data,
                                        status = HttpStatusCode.OK,
                                        headers = responseHeaders
                                    )
                                }
                                RocketResponseType.VALID_DATA -> {
                                    respond(
                                        RocketDataValid.data,
                                        status = HttpStatusCode.OK,
                                        headers = responseHeaders
                                    )
                                }
                            }
                        } else {
                            throw Exception("Stub!")
                        }
                    }
                }
            }
            return RocketServiceImpl(client)
        }
    }
}
