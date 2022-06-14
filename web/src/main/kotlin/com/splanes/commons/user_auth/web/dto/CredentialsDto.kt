package com.splanes.commons.user_auth.web.dto

import com.fasterxml.jackson.annotation.JsonAlias
import javax.validation.constraints.*

data class CredentialsDto(
    @field:JsonAlias("email")
    @field:Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")
    @field:NotBlank
    @field:NotNull
    val email: String,

    @field:JsonAlias("pwd")
    @field:Pattern(regexp = "^(?=^.{8,}\$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*\$")
    @field:NotBlank
    @field:NotNull
    val password: String
)
