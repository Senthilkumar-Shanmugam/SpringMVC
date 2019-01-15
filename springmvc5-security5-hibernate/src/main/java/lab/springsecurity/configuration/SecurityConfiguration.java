package lab.springsecurity.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//Spring Securiry related configuration
//contains authentication,authorization,login configs
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication()
		.withUser("admin").password(encoder().encode("adminPass")).roles("ADMIN")
		.and()
		.withUser("user").password(encoder().encode("userPass")).roles("USER");
	}
	
	@Override
	protected void configure(HttpSecurity security) throws Exception{
		security.authorizeRequests()
		        .antMatchers("/login").permitAll()
		        .antMatchers("/admin/**").hasRole("ADMIN")
		        .antMatchers("/**").hasAnyRole("ADMIN","USER")
		        .and().formLogin()
		        .and().logout().logoutSuccessUrl("/login").permitAll()
		        .and().csrf().disable();
	}
	
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
