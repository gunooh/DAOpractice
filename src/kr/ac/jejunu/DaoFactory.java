package kr.ac.jejunu;

/**
 * Created by PARK on 2017-04-20.
 */
public class DaoFactory {

    public UserDao getUserDao() {
        return new UserDao(getConnectionMaker());
    }

    private JejuConnectionMaker getConnectionMaker()
    {
        return new JejuConnectionMaker();
    }
}
