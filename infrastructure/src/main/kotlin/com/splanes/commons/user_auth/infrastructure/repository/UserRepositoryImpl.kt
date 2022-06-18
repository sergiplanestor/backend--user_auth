package com.splanes.commons.user_auth.infrastructure.repository

import com.splanes.commons.user_auth.domain.model.user.UserData
import com.splanes.commons.user_auth.domain.repository.UserRepository
import com.splanes.commons.user_auth.infrastructure.mapper.user.UserMapper
import com.splanes.commons.user_auth.infrastructure.repository.db.UserDatabaseRepository
import com.splanes.commons.user_auth.infrastructure.utils.awaitOrEmpty
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserRepositoryImpl(
  @Autowired private val dbRepository: UserDatabaseRepository,
  @Autowired private val mapper: UserMapper,
) : UserRepository {

  override suspend fun all(matcher: UserData.Matcher?): List<UserData.Detailed> =
    with(dbRepository) {
      when (matcher) {
        is UserData.Matcher.Alias -> findByAlias(matcher.value)
        is UserData.Matcher.Email -> findByEmail(matcher.value)
        is UserData.Matcher.Uuid -> findByUuid(matcher.value)
        else -> findAll()
      }
    }.awaitOrEmpty().map(mapper::domainOf)

  override suspend fun single(matcher: UserData.Matcher?): UserData.Detailed? =
    all(matcher).firstOrNull()
}