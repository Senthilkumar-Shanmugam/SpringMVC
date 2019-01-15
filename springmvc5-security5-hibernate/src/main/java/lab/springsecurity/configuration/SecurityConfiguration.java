package lab.springsecurity.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//Spring Securiry related configuration
//contains authentication,authorization,login configs
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception{
		/*auth.inMemoryAuthentication()
		.withUser("admin").password(encoder().encode("adminPass")).roles("ADMIN")
		.and()
		.withUser("user").password(encoder().encode("userPass")).roles("USER");*/
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests().anyRequest().hasAnyRole("ADMIN","USER")
		.and()
		.authorizeRequests().antMatchers("/login**").permitAll()
		.and()
		.formLogin().loginPage("/login").loginProcessingUrl("/loginAction").permitAll()
		.and()
		.logout().logoutSuccessUrl("/login").permitAll()
		.and()
		.csrf().disable();
	}
	}
