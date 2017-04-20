package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;  // SQL문을 사용하기 위해 필요

/**
 * Created by hyh0408 on 2017. 3. 15..
 */
public class UserDao {

    private JdbcContext jdbcContext;
    public UserDao()
    {

    }

    public User get(String id) throws ClassNotFoundException, SQLException {

        return jdbcContext.jdbcContextWithStatementStrategyForQuery(id, new StatementStrategy() {
            @Override
            public PreparedStatement makeStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement;
                preparedStatement = connection.prepareStatement("select * from user where id = ?");
                preparedStatement.setString(1, id);
                return preparedStatement;
            }
        });
    }

    // 파라미터안에 User 클래스의 user 객체가 들어가는 이유 : User 클래스의 getId 메소드를 받아오기 위해
    public void add(final User user) throws SQLException, ClassNotFoundException {

        jdbcContext.jdbcContextWithStatementStrategyForUpdate(new StatementStrategy() {
            @Override
            public PreparedStatement makeStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement;
                preparedStatement = connection.prepareStatement("insert into user(id, name, password) values(?, ?, ?)");

                preparedStatement.setString(1, user.getId());
                preparedStatement.setString(2, user.getName());
                preparedStatement.setString(3, user.getPassword());
                return  preparedStatement;
            }
        });
    }

    public void delete(String id) throws SQLException {

        jdbcContext.jdbcContextWithStatementStrategyForUpdate(new StatementStrategy(){
            @Override
            public PreparedStatement makeStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement;
                preparedStatement = connection.prepareStatement("delete from user where id = ?");
                preparedStatement.setString(1, id);
                return preparedStatement;
            }
        });
    }

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }
}
