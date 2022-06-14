group = "com.splanes.common.user_auth"

dependencies {

    libs module Domain

    libs add Dependencies.springWeb
    libs add Dependencies.swagger
    libs add Dependencies.jwt

    libs runtime Dependencies.jwtRuntime
}