package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component
public class UsersRepository implements CrudRepository<User> {
    private final JdbcTemplate jdbcTemplate;

    public UsersRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> findById(Long id) {
        String query = "SELECT * FROM cinema.users WHERE id=" + id;
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(User.class))
                .stream().findAny();
    }

    public Optional<User> findByEmail(String email) {
        String query = "SELECT * FROM cinema.users WHERE email='" + email + "'";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(User.class))
                .stream().findAny();
    }

    @Override
    public List<User> findAll() {
        String query = "SELECT * FROM cinema.users";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void save(User entity) {
        String query = "INSERT INTO cinema.users (firstname, secondname, email, password) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(query, entity.getFirstName(), entity.getSecondName(), entity.getEmail(), entity.getPassword());
    }

    @Override
    public void update(User entity) {
        String query = "UPDATE cinema.users SET firstname=?, secondname=?, email=?, password=? WHERE id=?";
        jdbcTemplate.update(query, entity.getFirstName(), entity.getSecondName(), entity.getEmail(), entity.getPassword(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM cinema.users WHERE id=?";
        jdbcTemplate.update(query, id);
    }
}
