package com.springboot.security

import javax.sql.DataSource

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.ClientAuthenticationMethod
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class AuthorizationServerConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests { authorizeRequests ->
				authorizeRequests.anyRequest().authenticated()
			}
			.oauth2ResourceServer { oauth2 ->
				oauth2.jwt(Customizer.withDefaults())
			}

		return http.build()
	}

	@Bean
	RegisteredClientRepository registeredClientRepository(DataSource dataSource) {
		return new JdbcRegisteredClientRepository(dataSource)
	}

	@Bean
	CommandLineRunner initClients(RegisteredClientRepository registeredClientRepository) {
		return { args ->
			if (registeredClientRepository.findByClientId('my-client-id') == null) {
				RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
						.clientId('my-client-id')
						.clientSecret('{noop}my-client-secret')
						.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
						.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
						.scope('read')
						.scope('write')
						.build()

				registeredClientRepository.save(registeredClient)
			}
		}
	}
}
