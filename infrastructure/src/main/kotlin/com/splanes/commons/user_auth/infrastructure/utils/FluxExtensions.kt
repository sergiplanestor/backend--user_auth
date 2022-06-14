package com.splanes.commons.user_auth.infrastructure.utils

import kotlinx.coroutines.TimeoutCancellationException
import reactor.core.publisher.Flux
import java.time.Duration

suspend fun <T> Flux<T>.awaitOrThrow(
    timeout: Duration = RUN_SUSPENDED_TIMEOUT,
    onTimeout: (TimeoutCancellationException) -> List<T> = { e -> throw e },
    onError: (Throwable) -> List<T> = { e -> throw e }
): List<T> =
    awaitSuspendedCatching(
        timeout = timeout.toMillis(),
        onTimeout = onTimeout,
        onError = onError,
        action = { collectList().block()!! }
    )

suspend fun <T> Flux<T>.awaitOrNull(timeout: Duration = RUN_SUSPENDED_TIMEOUT): List<T>? =
    runCatching { awaitOrThrow(timeout) }.getOrNull()

suspend fun <T> Flux<T>.awaitOrEmpty(timeout: Duration = RUN_SUSPENDED_TIMEOUT): List<T> =
    awaitOrNull(timeout).orEmpty()
