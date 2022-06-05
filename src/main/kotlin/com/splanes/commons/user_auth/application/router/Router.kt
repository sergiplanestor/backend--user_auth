package com.splanes.commons.user_auth.application.router

import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.server.coRouter

@RestController
class Router {

    @Bean
    fun routes(handler: ApiRequestHandler) = coRouter {
        "/".nest {
            GET("", handler::getStarredRepos)
        }
    }
}