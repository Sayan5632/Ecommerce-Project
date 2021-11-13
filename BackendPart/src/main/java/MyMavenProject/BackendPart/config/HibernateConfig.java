package MyMavenProject.BackendPart.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;



public class HibernateConfig {
	private String DATABASE_URL = "jdbc:h2:tcp://localhost/~/test";
	private String DATABASE_DRIVER = "org.h2.Driver";
	private String DATABASE_DIALECT = "org.hibernate.dialect.H2Dialect";
	private String DATABASE_USERNAME = "sayan";
	private String DATABASE_PASSWORD = "";

	
   //	DataSource	
	@Bean
	public DataSource getDataSource() {

		BasicDataSource dataSource = new BasicDataSource();

		// Providing the database connection information
		dataSource.setDriverClassName(DATABASE_DRIVER);
		dataSource.setUrl(DATABASE_URL);
		dataSource.setUsername(DATABASE_USERNAME);
		dataSource.setPassword(DATABASE_PASSWORD);

		return dataSource;

	}

	
	//	SessionFactory	
	@Bean
	public SessionFactory getSessionFactory(DataSource dataSource) {

		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);

		builder.addProperties((Properties) getHibernateProperties());
		builder.scanPackages("MyMavenProject.BackendPart.model");

		return builder.buildSessionFactory();

	}

	//	 HibernateProperties	
	private Object getHibernateProperties() {
		Properties properties = new Properties();

		properties.put("hibernate.dialect", DATABASE_DIALECT);
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");

		properties.put("hibernate.hbm2ddl.auto", "update");

		return properties;
	}
	

	//	Transaction
	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}

}
