package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by PARK on 2017-04-21.
 */
public class JdbcContext {
    private DataSource dataSource;

    public void jdbcContextWithStatementStrategyForUpdate(StatementStrategy statementStrategy) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();

            preparedStatement = statementStrategy.makeStatement(connection);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    public User jdbcContextWithStatementStrategyForQuery(String id, StatementStrategy statementStrategy) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = dataSource.getConnection();

//            preparedStatement = connection.prepareStatement("select * from user where id = ?");
            preparedStatement = statementStrategy.makeStatement(connection);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                user = new User();
                user.setId(resultSet.getString("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if(preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if(connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return user;
    }

    public void update(final String query, final String[] params) {
        jdbcContextWithStatementStrategyForUpdate(new StatementStrategy(){
            @Override
            public PreparedStatement makeStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement;
                preparedStatement = connection.prepareStatement(query);
                for(int i = 1; i<=params.length; i++)
                {
                    preparedStatement.setString(i, params[i-1]);
                }
                return preparedStatement;
            }
        });
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
