package com.example.demo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// Definisemo prava pristupa odredjenim URL-ovima
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.cors().and()	// ukljucuje cors konfiguraciju 
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.httpBasic().disable().formLogin().disable()
				// svim korisnicima dopusti da pristupe putanjama /auth/login
				.authorizeRequests().antMatchers("/auth/login").permitAll().and()
				// za svaki drugi zahtev korisnik mora biti autentifikovan
				.authorizeRequests().anyRequest().authenticated();
		
		http.csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// Ovim smo dozvolili pristup statickim resursima aplikacije
		web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "favicon.ico", "/**/*.html",
				"/**/*.css", "/**/*.js");
	}

}
