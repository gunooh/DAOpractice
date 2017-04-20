package kr.ac.jejunu;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;

/**
 * Created by PARK on 2017-04-15.
 */
public class UserDaoTest {

    private UserDao userDao;

    @Before
    public void setup() {
        ApplicationContext context = new GenericXmlApplicationContext("daoFactory.xml");
        userDao = context.getBean("userDao", UserDao.class);

    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        String id = "1";
        String name = "박건우";
        String password = "1234";
        User user = userDao.get(id);
        assertThat(id, is(user.getId()));
        assertThat(name, is(user.getName()));
        assertThat(password, is(user.getPassword()));
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        String id = String.valueOf(new Random().nextInt());
        String name = "박건우";
        String password = "1234";

        User user = new User();

        user.setId(id);
        user.setName(name);
        user.setPassword(password);

        userDao.add(user);

        User addedUser = userDao.get(id);

        assertThat(id, is(addedUser.getId()));
        assertThat(name, is(addedUser.getName()));
        assertThat(password, is(addedUser.getPassword()));
    }

    @Test
    public void delete() throws SQLException, ClassNotFoundException {
        String id = String.valueOf(new Random().nextInt());
        String name = "박건우";
        String password = "1234";

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);

        userDao.delete(id);
        assertNull(userDao.get(id));
    }

}