package ro.cmm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import ro.cmm.dao.CarDAO;
import ro.cmm.dao.MessageDAO;
import ro.cmm.dao.UserDAO;
import ro.cmm.dao.db.JdbcTemplateCarDAO;
import ro.cmm.dao.db.JdbcTemplateMessageDAO;
import ro.cmm.dao.db.JdbcTemplateUserDAO;
import ro.cmm.dao.inmemory.IMCarDAO;
import ro.cmm.dao.inmemory.IMMessageDAO;
import ro.cmm.dao.inmemory.IMUserDAO;

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


	@Bean
    public CarDAO carDAO() {
        return new IMCarDAO();
    }

    @Bean
    public UserDAO userDAO(){
        return new IMUserDAO();
    }

    @Bean
    public MessageDAO messageDAO(){
        return new IMMessageDAO();
    }

    //	@Bean
//   public EmployeeDAO employeeDAO() {
//		return new JDBCEmployeeDAO("localhost",
//				"5432",
//				"ems",
//				"sebi" ,
//				"sebi");
//   }
/*    @Bean
    public CarDAO carDAO() {

        return new JdbcTemplateCarDAO(dataSource());
    }*/

/*    @Bean
    public UserDAO userDAO(){
        return new JdbcTemplateUserDAO(dataSource());
    }*/

/*    @Bean
    public MessageDAO messageDAO(){
        return new JdbcTemplateMessageDAO(dataSource());
    }*/

    @Bean
    public DataSource dataSource() {
        String url = new StringBuilder()
                .append("jdbc:")
                .append("postgresql")
                .append("://")
                .append(dbHost)
                .append(":")
                .append("5432")
                .append("/")
                .append(dbName)
                .append("?user=")
                .append(dbUser)
                .append("&password=")
                .append(dbPassword).toString();

        return  new SingleConnectionDataSource(url, false);
    }


//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

}