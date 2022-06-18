package com.splanes.commons.user_auth.infrastructure.mapper.user

import com.splanes.commons.user_auth.domain.model.user.UserData
import com.splanes.commons.user_auth.infrastructure.entity.user.UserDataEntity
import com.splanes.commons.user_auth.infrastructure.mapper.Mapper
import java.sql.Date

class UserMapper : Mapper<UserData, UserDataEntity> {

    override fun domainOf(entity: UserDataEntity): UserData.Detailed = with(entity) {
        UserData.Detailed(
            uuid = uuid,
            email = email,
            alias = alias,
            name = name,
            lastName = lastName,
            dateCreatedOn = dateCreation,
            dateLastLogin = dateLastLogin
        )
    }

    override fun entityOf(bo: UserData) =
        when (bo) {
            is UserData.Detailed -> with(bo) {
                UserDataEntity(
                    uuid = uuid,
                    email = email,
                    name = name,
                    lastName = lastName,
                    alias = alias,
                    dateCreation = Date(dateCreatedOn.time),
                    dateLastLogin = Date(dateLastLogin.time)
                )
            }
            is UserData.Simple -> error("Simple user data model cannot be converted to entity.")
        }
}