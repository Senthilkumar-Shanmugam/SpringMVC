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

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailsService;
  
  @Autowired
  private PasswordEncoder passwordEncoder;
  
  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  };
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
  //  auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	  auth.inMemoryAuthentication()
	  .passwordEncoder(passwordEncoder)
	  .withUser("user").password(passwordEncoder.encode("user")).roles("USER")
	  .and()
	  .withUser("admin").password(passwordEncoder.encode("admin")).roles("ADMIN");
	  
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().
     antMatchers("/login").permitAll()
     .antMatchers("/**").hasAnyRole("ADMIN","USER")
     .and()
     .formLogin()
     .loginPage("/login")
     .defaultSuccessUrl("/home")
     .failureUrl("/login?error=true")
     .permitAll()
     .and()
     .logout()
     .logoutSuccessUrl("/login?logout=true")
     .invalidateHttpSession(true)
     .and()
     .csrf()
     .disable();
  }
}
