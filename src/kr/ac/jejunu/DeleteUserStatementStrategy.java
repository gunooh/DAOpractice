package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by PARK on 2017-04-21.
 */
public class DeleteUserStatementStrategy implements StatementStrategy {

    private String id;
    public DeleteUserStatementStrategy(String id) {
        this.id = id;
    }
    public PreparedStatement makeStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("delete from user where id = ?");
        preparedStatement.setString(1, id);
        return preparedStatement;
    }
}
