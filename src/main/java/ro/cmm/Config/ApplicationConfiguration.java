package ro.cmm.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ro.cmm.dao.CarDAO;
import ro.cmm.dao.MessageDAO;
import ro.cmm.dao.UserDAO;
import ro.cmm.dao.db.JdbcTemplateCarDAO;
import ro.cmm.dao.db.JdbcTemplateMessageDAO;
import ro.cmm.dao.db.JdbcTemplateUserDAO;

import javax.sql.DataSource;

@Configuration
public class ApplicationConfiguration {

    @Value("${db.host}")
    private String dbHost;

    @Value("${db.password}")
    private String dbPassword;

    @Value("${db.user}")
    private String dbUser;

    @Value("${db.name}")
    private String dbName;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Bean
    public CarDAO carDAO() {

        return new JdbcTemplateCarDAO(dataSource());
    }

    @Bean
    public UserDAO userDAO(){
        return new JdbcTemplateUserDAO(dataSource());
    }

    @Bean
    public MessageDAO messageDAO(){
        return new JdbcTemplateMessageDAO(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        String url = "postgres://aaxeadkvhfddxl:13ee3140809d99629682f19252fbd30040c4e299413e062a7f2bbcf10b29a12e@ec2-54-217-205-90.eu-west-1.compute.amazonaws.com:5432/d7ji2n7t0lq3e?ssslmode=require";
        return  new SingleConnectionDataSource(url, false);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        JdbcDaoImpl jdbcImpl = new JdbcDaoImpl();
        jdbcImpl.setDataSource(dataSource());
        jdbcImpl.setUsersByUsernameQuery(usersQuery);
        jdbcImpl.setAuthoritiesByUsernameQuery(rolesQuery);
        return jdbcImpl;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}