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

        StatementStrategy statementStrategy = new GetUserStatementStrategy(id);

        return jdbcContext.jdbcContextWithStatementStrategyForQuery(id, statementStrategy);
    }

    // 파라미터안에 User 클래스의 user 객체가 들어가는 이유 : User 클래스의 getId 메소드를 받아오기 위해
    public void add(User user) throws SQLException, ClassNotFoundException {
        StatementStrategy statementStrategy = new AddUserStatementStrategy(user);

        jdbcContext.jdbcContextWithStatementStrategyForUpdate(statementStrategy);
    }

    public void delete(String id) throws SQLException {
        StatementStrategy statementStrategy = new DeleteUserStatementStrategy(id);

        jdbcContext.jdbcContextWithStatementStrategyForUpdate(statementStrategy);
    }

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }
}
