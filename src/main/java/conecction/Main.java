package conecction;

import redis.clients.jedis.Jedis;

public class Main {

        public static void main(String[] args) {

                DBConnection connection = new DBConnection();
                final Jedis jedis = connection.connect();
        }
}
