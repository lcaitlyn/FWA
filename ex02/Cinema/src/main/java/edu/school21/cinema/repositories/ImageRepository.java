package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Image;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class ImageRepository {
    private final JdbcTemplate jdbcTemplate;

    public ImageRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void save(Image entity) {
        final String query = "INSERT INTO cinema.images (userid, name, mime) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, entity.getUserID(), entity.getName(), entity.getMIME());
    }

    public Optional<Image> findByID(Long id) {
        final String query = "SELECT * FROM cinema.images WHERE id=" + id;
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Image.class))
                .stream().findAny();
    }
    public Optional<Image> findLastByUserID(Long userId) {
        final String query = "SELECT * FROM cinema.images WHERE userid=" + userId + " ORDER BY id DESC LIMIT 1";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Image.class))
                .stream().findAny();
    }

    public List<Image> findAllByUserID(Long userId) {
        final String query = "SELECT * FROM cinema.images WHERE userid=" + userId + " ORDER BY id DESC";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Image.class));
    }

    public void delete(Long id) {
        final String query = "DELETE FROM cinema.images WHERE id=" + id;
        jdbcTemplate.update(query);
    }
}
