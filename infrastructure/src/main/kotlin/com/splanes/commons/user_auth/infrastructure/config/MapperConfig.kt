package com.splanes.commons.user_auth.infrastructure.config

import com.splanes.commons.user_auth.infrastructure.mapper.user.UserMapper
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope

@Configuration
class MapperConfig {

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    fun userMapper(): UserMapper = UserMapper()
}