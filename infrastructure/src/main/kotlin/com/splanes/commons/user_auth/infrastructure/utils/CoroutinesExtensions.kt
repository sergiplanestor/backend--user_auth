package com.splanes.commons.user_auth.infrastructure.utils

import kotlinx.coroutines.*
import java.time.Duration
import java.time.temporal.ChronoUnit
import kotlin.coroutines.*

val RUN_SUSPENDED_TIMEOUT: Duration get() = Duration.of(30, ChronoUnit.SECONDS)

suspend fun <T> awaitSuspended(timeout: Long = RUN_SUSPENDED_TIMEOUT.toMillis(), action: () -> T): T =
    withTimeout(timeout) {
        suspendCoroutine { continuation ->
            runCatching(action).fold(
                onSuccess = continuation::resume,
                onFailure = continuation::resumeWithException
            )
        }
    }

suspend fun <T> awaitSuspendedCatching(
    timeout: Long = RUN_SUSPENDED_TIMEOUT.toMillis(),
    context: CoroutineContext = Dispatchers.IO,
    onTimeout: (TimeoutCancellationException) -> T = { error -> throw error },
    onError: (Throwable) -> T = { error -> throw error },
    action: () -> T
): T =
    runCatching {
        withContext(context) {
            awaitSuspended(timeout, action)
        }
    }.getOrElse { error ->
        when (error) {
            is TimeoutCancellationException -> onTimeout(error)
            else -> onError(error)
        }
    }