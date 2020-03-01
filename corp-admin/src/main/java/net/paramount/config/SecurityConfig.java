/*
 * Copyright 2016-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.paramount.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring Security Configuration.
 *
 * @author Marcelo Fernandes
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Inject
	private AuthenticationProvider corpAuthenticationProvider;

	//@Inject
	//private CorporateAuthenticationProvider authProvider;

/*	@Inject
	private AuthenticationEntryPoint authEntryPoint;*/
	
	@Configuration
	@Order(1)
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		protected void configure(HttpSecurity http) throws Exception {
			// rest Login
			http.antMatcher("/api/**").authorizeRequests().anyRequest().fullyAuthenticated()/*.hasRole("ADMIN")*/.and().httpBasic().and().csrf()
					.disable();
		}
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(corpAuthenticationProvider/*authProvider*/);
	}
	
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(buildUnauthorizedMatchers()).permitAll()
		.antMatchers("/", "/login.xhtml", "/javax.faces.resource/**").permitAll().anyRequest()
		.fullyAuthenticated().and().formLogin().loginPage("/login.xhtml").defaultSuccessUrl("/index.xhtml")
		.failureUrl("/login.xhtml?authfailed=true").permitAll().and().logout().logoutSuccessUrl("/index.xhtml")
		.logoutUrl("/j_spring_security_logout").and().csrf().disable();
		
		// allow to use resource links like pdf
		http.headers().frameOptions().sameOrigin();
		
		//http.httpBasic().authenticationEntryPoint(authEntryPoint);
		
		http.csrf().disable();
	}*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		handleSecuredConfiguration(http);
	}
	
	private String[] buildUnauthorizedMatchers() {
		String[] unauthorizedPatterns = new String[] { 
				//"/api/**", 
				"/protected/**", 
				"/*", 
				"/public/**", 
				"/resources/**", 
				"/includes/**", 
				"/pages/**", 
				"/auth/register/**",
				"/login.xhtml", 
				"/javax.faces.resource/**"
		};
		return unauthorizedPatterns;
	}

	private void handleSecuredConfiguration(HttpSecurity http) throws Exception {
    // require all requests to be authenticated except for the resources
    http.authorizeRequests().antMatchers(buildUnauthorizedMatchers())
        .permitAll().anyRequest().authenticated();
    // login
    http.formLogin().loginPage("/login.xhtml").permitAll()
        .failureUrl("/login.xhtml?error=true");
    // logout
    http.logout().logoutSuccessUrl("/index.jsf");
    // not needed as JSF 2.2 is implicitly protected against CSRF
    http.csrf().disable();
	}
}
