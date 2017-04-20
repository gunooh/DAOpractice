package kr.ac.jejunu;

import org.junit.Test;

import java.sql.SQLException;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by PARK on 2017-04-15.
 */
public class UserDaoTest {
    @Test
    public void Jejuget() throws SQLException, ClassNotFoundException {
        UserDao userDao = new JejuUserDao();
        String id = "1";
        String name = "박건우";
        String password = "1234";
        User user = userDao.get(id);
        assertThat(id, is(user.getId()));
        assertThat(name, is(user.getName()));
        assertThat(password, is(user.getPassword()));
    }

    @Test
    public void Jejuadd() throws SQLException, ClassNotFoundException {
        String id = String.valueOf(new Random().nextInt());
        String name = "박건우";
        String password = "1234";

        User user = new User();

        user.setId(id);
        user.setName(name);
        user.setPassword(password);

        UserDao userDao = new JejuUserDao();

        userDao.add(user);

        User addedUser = userDao.get(id);

        assertThat(id, is(addedUser.getId()));
        assertThat(name, is(addedUser.getName()));
        assertThat(password, is(addedUser.getPassword()));
    }


    @Test
    public void Hallaget() throws SQLException, ClassNotFoundException {
        UserDao userDao = new HallaUserDao();
        String id = "1";
        String name = "박건우";
        String password = "1234";
        User user = userDao.get(id);
        assertThat(id, is(user.getId()));
        assertThat(name, is(user.getName()));
        assertThat(password, is(user.getPassword()));
    }


    @Test
    public void Hallaadd() throws SQLException, ClassNotFoundException {
        String id = String.valueOf(new Random().nextInt());
        String name = "박건우";
        String password = "1234";

        User user = new User();

        user.setId(id);
        user.setName(name);
        user.setPassword(password);

        UserDao userDao = new HallaUserDao();

        userDao.add(user);

        User addedUser = userDao.get(id);

        assertThat(id, is(addedUser.getId()));
        assertThat(name, is(addedUser.getName()));
        assertThat(password, is(addedUser.getPassword()));
    }

}