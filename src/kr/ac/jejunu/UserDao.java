package kr.ac.jejunu;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.*;  // SQL문을 사용하기 위해 필요

/**
 * Created by hyh0408 on 2017. 3. 15..
 */
public class UserDao {

    private JdbcTemplate jdbcTemplate;
    public UserDao()
    {

    }

    public User get(String id) throws ClassNotFoundException, SQLException {

        String sql = "select * from user where id = ?";
        Object[] args = new String[] {id};
        User queryForObject = null;
        try {
            queryForObject = getJdbcTemplate().queryForObject(sql, args, new RowMapper<User>(){

                @Override
                public User mapRow(ResultSet rs, int rownum) throws SQLException {
                    User user = new User();
                    user.setId(rs.getString("id"));
                    user.setName(rs.getString("name"));
                    user.setPassword(rs.getString("password"));
                    return user;
                }
            });
        } catch (EmptyResultDataAccessException e) {
        }
        return queryForObject;
    }

    // 파라미터안에 User 클래스의 user 객체가 들어가는 이유 : User 클래스의 getId 메소드를 받아오기 위해
    public void add(final User user) throws SQLException, ClassNotFoundException {

        String query = "insert into user(id, name, password) values(?, ?, ?)";
        String params[] = new String[] {user.getId(), user.getName(), user.getPassword()};

        jdbcTemplate.update(query, params);
    }

    public void delete(String id) throws SQLException {

        final String query = "delete from user where id = ?";
        final String params[] = new String[] {id};

        jdbcTemplate.update(query, params);
    }

    public JdbcTemplate getJdbcTemplate()
    {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
