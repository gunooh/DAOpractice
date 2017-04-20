package kr.ac.jejunu;

import org.springframework.context.annotation.Bean;

/**
 * Created by PARK on 2017-04-20.
 */
public class DaoFactory {
    @Bean
    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }

    @Bean
    public ConnectionMaker connectionMaker() {
        return new JejuConnectionMaker();
    }


}
