package br.com.wellingtoncosta.demo.configuration

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("br.com.wellingtoncosta.demo.repository")
class DatabaseConfiguration {

    @Bean
    @FlywayDataSource
    @Throws(Exception::class)
    fun dataSource(dataSourceProperties: DataSourceProperties): DataSource {
        val config = HikariConfig()

        config.driverClassName = dataSourceProperties.driverClassName
        config.jdbcUrl = dataSourceProperties.url
        config.username = dataSourceProperties.username
        config.password = dataSourceProperties.password

        return HikariDataSource(config)
    }

}