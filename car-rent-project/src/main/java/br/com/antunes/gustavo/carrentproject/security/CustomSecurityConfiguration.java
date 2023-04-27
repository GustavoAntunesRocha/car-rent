package br.com.antunes.gustavo.carrentproject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class CustomSecurityConfiguration {

	private final JwtAuthenticationFilter jwtAuthFilter;

	private final AuthenticationProvider authenticationProvider;

	public CustomSecurityConfiguration(JwtAuthenticationFilter jwtAuthFilter,
			AuthenticationProvider authenticationProvider) {
		super();
		this.jwtAuthFilter = jwtAuthFilter;
		this.authenticationProvider = authenticationProvider;
	}

	@Bean
	SecurityFilterChain applicationSecurity(HttpSecurity http) throws Exception {
				
		http
			.cors().disable()
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authenticationProvider(authenticationProvider)
			.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
			.formLogin().disable()
			.securityMatcher("/**")
			.authorizeHttpRequests(registry -> registry
					.requestMatchers("/swagger-ui/**").permitAll()
					.requestMatchers("/v3/**").permitAll()
					.requestMatchers("/api/v1/user/create").permitAll()
					.requestMatchers("/api/v1/user/login").permitAll()
					.requestMatchers("/api/v1/customer").permitAll()
					.requestMatchers("/api/v1/location/city").permitAll()
					.anyRequest().authenticated()
					
			);
		
		http.headers((headers) -> headers.frameOptions().sameOrigin());
		return http.build();
	}

}
