package com.splanes.commons.user_auth.infrastructure.utils

import java.util.*

fun <T> Optional<T>.orNull(): T? = orElse(null)

fun <T> Optional<T>.toResult(): Result<T> = runCatching { orElseThrow() }

fun <T> Optional<T>.toNullableResult(): Result<T?> = runCatching { orNull() }

fun <T : Any> Result<T>.toOptional(): Optional<T> = fold(
    onSuccess = { v -> Optional.of(v) },
    onFailure = { Optional.empty() }
)