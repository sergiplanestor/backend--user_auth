package com.splanes.commons.user_auth.domain.config

import com.splanes.commons.user_auth.domain.model.user.UserData
import com.splanes.commons.user_auth.domain.repository.UserRepository
import com.splanes.commons.user_auth.domain.service.credentials.CredentialsService
import com.splanes.commons.user_auth.domain.service.credentials.impl.CredentialsServiceImpl
import com.splanes.commons.user_auth.domain.service.user.UserService
import com.splanes.commons.user_auth.domain.service.user.impl.UserServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ServiceConfig {

	@Bean
	fun credentialsService(): CredentialsService =
		CredentialsServiceImpl()

	@Bean(name = ["userSimpleService"])
	fun userSimpleService(repository: UserRepository): UserService<UserData.Simple> =
		UserServiceImpl.Simple(repository)

	@Bean(name = ["userDetailedService"])
	fun userDetailedService(repository: UserRepository): UserService<UserData.Detailed> =
		UserServiceImpl.Detailed(repository)

}