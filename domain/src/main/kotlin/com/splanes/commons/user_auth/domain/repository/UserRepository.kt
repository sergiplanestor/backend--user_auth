package com.splanes.commons.user_auth.domain.repository

import com.splanes.commons.user_auth.domain.model.user.UserData


interface UserRepository {
    suspend fun all(matcher: UserData.Matcher? = null): List<UserData.Detailed>
    suspend fun single(matcher: UserData.Matcher? = null): UserData.Detailed?
}