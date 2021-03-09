package conecction;

import redis.clients.jedis.Jedis;

public class DBConnection {

        private String host = "127.0.0.1";
        private int port = 6379;
        private String password = "";
        private int timeout = 10;

        public Jedis connect() {
                try {
                        Jedis jedis = new Jedis(host, port, timeout);

                        if (!password.isEmpty()){
                                jedis.auth(password);
                        }

                        return jedis;
                } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                }
        }
}
