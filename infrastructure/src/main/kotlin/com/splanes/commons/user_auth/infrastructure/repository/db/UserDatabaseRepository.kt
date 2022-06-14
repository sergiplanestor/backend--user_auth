package com.splanes.commons.user_auth.infrastructure.repository.db

import com.splanes.commons.user_auth.infrastructure.entity.user.UserDataEntity
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface UserDatabaseRepository : ReactiveCrudRepository<UserDataEntity, Long> {

    @Query("SELECT u FROM tbl_user_data u WHERE u.uuid LIKE :uuid")
    fun findByUuid(uuid: String): Flux<UserDataEntity>

    @Query("SELECT u FROM tbl_user_data u WHERE u.email LIKE :email")
    fun findByEmail(email: String): Flux<UserDataEntity>

    @Query("SELECT u FROM tbl_user_data u WHERE u.alias LIKE %:alias%")
    fun findByAlias(alias: String): Flux<UserDataEntity>
}