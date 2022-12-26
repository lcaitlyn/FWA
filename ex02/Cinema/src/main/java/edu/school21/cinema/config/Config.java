package edu.school21.cinema.config;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.cinema.repositories.ImageRepository;
import edu.school21.cinema.repositories.LogsRepository;
import edu.school21.cinema.repositories.UsersRepository;
import edu.school21.cinema.services.ImageService;
import edu.school21.cinema.services.ImageServiceImpl;
import edu.school21.cinema.services.UsersService;
import edu.school21.cinema.services.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@ComponentScan("edu.school21.cinema")
@PropertySource("classpath:../application.properties")
public class Config {
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;
    @Value("${db.driver.name}")
    private String driver;
    @Value("${storage.path}")
    private String storagePath;

    @Bean
    public HikariDataSource hikariDataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();

        hikariDataSource.setJdbcUrl(url);
        hikariDataSource.setUsername(username);
        hikariDataSource.setPassword(password);
        hikariDataSource.setDriverClassName(driver);

        return hikariDataSource;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UsersRepository usersRepository() {
        return new UsersRepository(hikariDataSource());
    }

    @Bean
    public LogsRepository logsRepository() {
        return new LogsRepository(hikariDataSource());
    }

    @Bean
    public UsersService usersService() {
        return new UsersServiceImpl(usersRepository(), bCryptPasswordEncoder());
    }

    @Bean
    public ImageRepository imageRepository() {
        return new ImageRepository(hikariDataSource());
    }

    @Bean
    public ImageService imageService() {
        return new ImageServiceImpl(imageRepository());
    }

    @Bean(value = "storagePath")
    public String getStoragePath() {
        return (!storagePath.startsWith("/")) ? "/" + storagePath : storagePath;
    }
}
