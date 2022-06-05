package com.splanes.commons.user_auth.application

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyAndAwait

@Component
class ApiRequestHandler(
    val service: SampleService
) {

    suspend fun repositoryOf(request: ServerRequest): ServerResponse =
        ok().bodyAndAwait(service.userRepositories())
}