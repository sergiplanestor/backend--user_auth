package com.splanes.commons.user_auth.infrastructure.utils

import kotlinx.coroutines.TimeoutCancellationException
import reactor.core.publisher.Mono
import java.time.Duration

suspend fun <T> Mono<T>.awaitOrThrow(
    timeout: Duration = RUN_SUSPENDED_TIMEOUT,
    onTimeout: (TimeoutCancellationException) -> T = { e -> throw e },
    onError: (Throwable) -> T = { e -> throw e }
): T =
    awaitSuspendedCatching(
        timeout = timeout.toMillis(),
        onTimeout = onTimeout,
        onError = onError,
        action = { block()!! }
    )

suspend fun <T> Mono<T>.awaitOrNull(timeout: Duration = RUN_SUSPENDED_TIMEOUT): T? =
    runCatching { awaitOrThrow(timeout) }.getOrNull()

suspend fun <T> Mono<T>.awaitResult(
    allowNullable: Boolean = false,
    timeout: Duration = RUN_SUSPENDED_TIMEOUT
): Result<T?> =
    runCatching { awaitOrNull(timeout) }.run {
        if (exceptionOrNull() is NullPointerException && allowNullable) {
            Result.success(null)
        } else {
            this
        }
    }