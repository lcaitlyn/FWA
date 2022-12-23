package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Log;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LogsRepository {
    private final JdbcTemplate jdbcTemplate;

    public LogsRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Log> findAll(String email) {
        final String query = "SELECT * FROM cinema.logs WHERE email='" + email + "'";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Log.class));
    }

    public void save(Log entity) {
        final String query = "INSERT INTO cinema.logs (email, date, ip) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, entity.getEmail(), entity.getDate(), entity.getIp());
    }
}
