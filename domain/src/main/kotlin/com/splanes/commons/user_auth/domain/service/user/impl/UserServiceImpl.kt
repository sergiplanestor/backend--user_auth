package com.splanes.commons.user_auth.domain.service.user.impl

import com.splanes.commons.user_auth.domain.model.user.UserData
import com.splanes.commons.user_auth.domain.model.user.simplify
import com.splanes.commons.user_auth.domain.repository.UserRepository
import com.splanes.commons.user_auth.domain.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

sealed class UserServiceImpl(protected open val repository: UserRepository) {

	@Service
	class Simple(
		@Autowired override val repository: UserRepository
	) : UserServiceImpl(repository), UserService.Simple {

		override suspend fun all(matcher: UserData.Matcher?): List<UserData.Simple> =
			repository
					.all(matcher)
					.map { u -> u.simplify() }

		override suspend fun single(matcher: UserData.Matcher?): UserData.Simple? =
			repository
					.single(matcher)
					?.simplify()
	}

	@Service
	class Detailed(
		@Autowired override val repository: UserRepository
	) : UserServiceImpl(repository), UserService.Detailed {

		override suspend fun all(matcher: UserData.Matcher?): List<UserData.Detailed> =
			repository.all(matcher)

		override suspend fun single(matcher: UserData.Matcher?): UserData.Detailed? =
			repository.single(matcher)
	}
}