package lab.springmvc.data.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScans(value= {@ComponentScan("lab.springmvc.data.jpa.service"),@ComponentScan("lab.springmvc.data.jpa.dao")})
public class AppConfig {

	
	@Bean
	public LocalEntityManagerFactoryBean getLocalEntityManagerFactoryBean() {
		LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
		factoryBean.setPersistenceUnitName("LOCAL_PERSISTENCE");
		return factoryBean;
	}
	
	@Bean
	public JpaTransactionManager getJpaTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(getLocalEntityManagerFactoryBean().getObject());
		return transactionManager;
	}
}
