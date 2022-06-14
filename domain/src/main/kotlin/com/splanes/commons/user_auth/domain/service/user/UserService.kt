package com.splanes.commons.user_auth.domain.service.user

import com.splanes.commons.user_auth.domain.model.user.UserData
import com.splanes.commons.user_auth.domain.repository.UserRepository

sealed interface UserService<out U : UserData> {
    val repository: UserRepository
    suspend fun all(matcher: UserData.Matcher? = null): List<U>
    suspend fun single(matcher: UserData.Matcher? = null): U?

    interface Simple : UserService<UserData.Simple>
    interface Detailed : UserService<UserData.Detailed>
}