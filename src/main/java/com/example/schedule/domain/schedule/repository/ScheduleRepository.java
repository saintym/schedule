package com.example.schedule.domain.schedule.repository;

import com.example.schedule.domain.schedule.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ScheduleRepository implements ScheduleRepositoryInterface {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Schedule> scheduleRowMapper = (rs, rowNum) -> new Schedule(
            rs.getInt("id"),
            rs.getString("todo"),
            rs.getString("user"),
            rs.getString("password"),
            rs.getTimestamp("created_at").toLocalDateTime(),
            rs.getTimestamp("updated_at").toLocalDateTime()
    );

    public Schedule save(Schedule schedule) {
        if (schedule.getId() == 0) {
            String sql = "INSERT INTO schedule (todo, user, password) VALUES (?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                ps.setString(1, schedule.getTodo());
                ps.setString(2, schedule.getUser());
                ps.setString(3, schedule.getPassword());
                return ps;
            }, keyHolder);

            return new Schedule(
                    keyHolder.getKey().intValue(),
                    schedule.getTodo(),
                    schedule.getUser(),
                    schedule.getPassword(),
                    schedule.getCreatedAt(),
                    schedule.getUpdatedAt()
            );
        } else {
            String sql = "UPDATE schedule SET todo = ?, user = ?, password = ? WHERE id = ?";
            jdbcTemplate.update(sql,
                    schedule.getTodo(),
                    schedule.getUser(),
                    schedule.getPassword(),
                    schedule.getId());
            return schedule;
        }
    }

    public Schedule findById(int id) {
        String sql = "SELECT * FROM schedule WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, scheduleRowMapper, id);
    }

    public List<Schedule> findAll(LocalDateTime start, LocalDateTime end, String keyword) {
        StringBuilder sql = new StringBuilder("SELECT * FROM schedule WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (start != null) {
            sql.append(" AND updated_at >= ?");
            params.add(Timestamp.valueOf(start));
        }
        if (end != null) {
            sql.append(" AND updated_at <= ?");
            params.add(Timestamp.valueOf(end));
        }
        if (keyword != null && !keyword.isEmpty()) {
            sql.append(" AND user LIKE ?");
            params.add("%" + keyword + "%");
        }

        return jdbcTemplate.query(sql.toString(), scheduleRowMapper, params.toArray());
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM schedule WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}