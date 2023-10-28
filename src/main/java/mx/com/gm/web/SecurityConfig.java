package mx.com.gm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration // con esto le decimos a Spring que es una clase de configuración
@EnableWebSecurity // con esto habilitamos la configuración de seguridad
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// A este concepto se le conoce como 'autorización'
		http.authorizeRequests().
			antMatchers("/editar/**", "/agregar/**", "/eliminar"). // Restringimos los path a los que se pueden acceder
				hasRole("ADMIN"). // Sólo los usuarios que tengan el rol de ADMIN podrán acceder
			antMatchers("/"). // Ahora epecificamos quién puede acceder al path raíz
				hasAnyRole("USER", "ADMIN").
			and().
				formLogin().
				loginPage("/login").
			and().
				exceptionHandling().accessDeniedPage("/errores/403");
	}
}
