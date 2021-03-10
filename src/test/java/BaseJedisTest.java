import conecction.DBConnection;
import org.junit.Before;
import redis.clients.jedis.Jedis;

public class BaseJedisTest {

        Jedis jedis;
        DBConnection connection = new DBConnection();

        public BaseJedisTest() {
                jedis = connection.connect();
        }
}
