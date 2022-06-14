package com.splanes.commons.user_auth.infrastructure.config

import com.splanes.commons.user_auth.infrastructure.repository.db.CredentialsDatabaseRepository
import io.r2dbc.spi.ConnectionFactory
import org.mariadb.r2dbc.MariadbConnectionConfiguration
import org.mariadb.r2dbc.MariadbConnectionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "infra.db.population")
class DatabasePopulationResources {
	lateinit var functions: List<String>
}

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
@EnableR2dbcRepositories(basePackageClasses = [CredentialsDatabaseRepository::class])
class ReactiveDatabaseConfig(
	@Value("\${infra.db.access.user}") private val user: String,
	@Value("\${infra.db.access.secret}") private val password: String,
	@Value("\${infra.db.connection.host}") private val host: String,
	@Value("\${infra.db.connection.port}") private val port: String,
	@Value("\${infra.db.name}") private val database: String,
	@Value("\${infra.db.population.schema}") private val dbSchema: String,
	@Value("\${infra.db.population.views}") private val dbViews: String,
	@Value("\${infra.db.population.data}") private val populationData: String,
	@Autowired private val proceduresAndTriggers: DatabasePopulationResources
) : AbstractR2dbcConfiguration() {

	@Bean
	override fun connectionFactory(): ConnectionFactory = MariadbConnectionConfiguration.builder()
			.host(host).port(port.toInt())
			.username(user).password(password)
			.database(database)
			.build()
			.run(MariadbConnectionFactory::from)

	@Bean
	fun connectionFactoryInitializer(connectionFactory: ConnectionFactory): ConnectionFactoryInitializer =
		ConnectionFactoryInitializer().apply {
			setConnectionFactory(connectionFactory)
			setDatabasePopulator(
				CompositeDatabasePopulator(
					ResourceDatabasePopulator(ClassPathResource(dbSchema)),
					ResourceDatabasePopulator(ClassPathResource(dbViews)),
					ResourceDatabasePopulator(*proceduresAndTriggers.functions.map { ClassPathResource(it) }.toTypedArray()).apply { setSeparator("//") },
					ResourceDatabasePopulator(ClassPathResource(populationData)),
				)
			)
		}
}