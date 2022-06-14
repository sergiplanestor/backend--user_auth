package com.splanes.commons.user_auth.infrastructure.entity.user

import com.splanes.commons.user_auth.domain.model.user.UserData


fun UserData.Matcher.match(entity: UserDataEntity): Boolean =
    when (this) {
        is UserData.Matcher.Alias -> entity.alias
        is UserData.Matcher.Email -> entity.email
        is UserData.Matcher.Uuid -> entity.uuid
    }.let { matcher ->
        matcher == value
    }