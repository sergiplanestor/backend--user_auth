package com.splanes.commons.user_auth.infrastructure.mapper

interface Mapper<Domain, Entity> {
    fun domainOf(entity: Entity): Domain
    fun entityOf(bo: Domain): Entity
    fun domainListOf(entities: List<Entity>): List<Domain> = entities.map(::domainOf)
    fun entityListOf(bos: List<Domain>): List<Entity> = bos.map(::entityOf)
}