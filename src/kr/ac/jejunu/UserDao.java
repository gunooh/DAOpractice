package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;  // SQL문을 사용하기 위해 필요

/**
 * Created by hyh0408 on 2017. 3. 15..
 */
public class UserDao {

    private DataSource dataSource;

    public UserDao()
    {

    }

    public User get(String id) throws ClassNotFoundException, SQLException {

        Connection connection = dataSource.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("select * from user where id = ?");
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        User user = new User();
        user.setId(resultSet.getString("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return user;
    }

    // 파라미터안에 User 클래스의 user 객체가 들어가는 이유 : User 클래스의 getId 메소드를 받아오기 위해
    public void add(User user) throws SQLException, ClassNotFoundException {
        Connection connection = dataSource.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("insert into user(id, name, password) values(?, ?, ?)");
        preparedStatement.setString(1, user.getId());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(3, user.getPassword());

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
