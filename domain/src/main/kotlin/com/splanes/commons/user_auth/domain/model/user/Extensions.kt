package com.splanes.commons.user_auth.domain.model.user

inline fun <reified M : UserData.Matcher> UserData.Matcher.Companion.of(user: UserData): M =
    when (M::class) {
        UserData.Matcher.Uuid::class -> UserData.Matcher.Uuid(user.uuid)
        UserData.Matcher.Email::class -> UserData.Matcher.Email(user.email)
        UserData.Matcher.Alias::class -> UserData.Matcher.Alias(user.alias)
        else -> error("Not supported UserData.Matcher type provided.")
    } as M

fun UserData.Matcher.match(user: UserData): Boolean =
    when (this) {
        is UserData.Matcher.Alias -> user.alias
        is UserData.Matcher.Email -> user.email
        is UserData.Matcher.Uuid -> user.uuid
    }.let { matcher ->
        matcher == value
    }

fun UserData.Detailed.simplify(): UserData.Simple =
    UserData.Simple(
        uuid = uuid,
        email = email,
        alias = alias
    )