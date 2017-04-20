package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Created by PARK on 2017-04-21.
 */


public class GetUserStatementStrategy implements StatementStrategy  {
    public PreparedStatement makeStatement(Object object, Connection connection) throws SQLException {
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("select * from user where id = ?");
        preparedStatement.setString(1, (String) object);
        return preparedStatement;
    }
}
