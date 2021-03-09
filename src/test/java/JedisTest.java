import conecction.DBConnection;
import jdk.jfr.Description;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

public class JedisTest {

        private Jedis jedis;
        private DBConnection connection = new DBConnection();
        String key = "key";
        String value = "Hello world";
        String valueToConcat = " Cesar";
        String key1 = "key1";
        String value1 = "value1";

        @Before
        public void beforeTest() {
                jedis = connection.connect();
        }

        @Test
        @Description("Testing Set Strings")
        public void testSetString() {

                jedis.set(key, value);

                Assert.assertEquals(value, jedis.get(key));

                jedis.del(key);
        }

        @Test
        @Description("Testing Set Strings")
        public void testLengthString() {

                jedis.set(key, value);

                Assert.assertEquals(Long.valueOf(value.length()), jedis.strlen(key));

                jedis.del(key);
        }

        @Test
        @Description("Testing Append Strings")
        public void testAppendString() {

                jedis.set(key, value);
                jedis.append(key, valueToConcat);

                Assert.assertEquals("Hello world Cesar", jedis.get(key));

                jedis.del(key);
        }

        @Test
        @Description("Testing multiset Strings")
        public void testMultiSetString() {

                jedis.mset(key, value, key1, value1);

                Assert.assertTrue(jedis.exists(key));
                Assert.assertTrue(jedis.exists(key1));
                Assert.assertEquals(value, jedis.get(key));
                Assert.assertEquals(value1, jedis.get(key1));

                jedis.del(key);
                jedis.del(key1);
        }

        @Test
        @Description("Testing substring for Strings")
        public void testRangeString() {
                jedis.set(key, value);

                //like a substring
                final String result = jedis.getrange(key, 0, 4);

                Assert.assertEquals("Hello", result);

                jedis.del(key);
        }

        @Test
        @Description("Testing Strings with expiration time in seconds")
        public void testSetWithExpirationInSecondsString() throws InterruptedException {

                jedis.setex(key, 5, value);
                Assert.assertTrue(jedis.exists(key));
                Thread.sleep(5000);
                Assert.assertFalse(jedis.exists(key));

                jedis.del(key);
        }

        @Test
        @Description("Testing Strings with expiration time in millis")
        public void testSetWithExpirationInMillisString() throws InterruptedException {

                jedis.psetex(key, 5000l, value);
                Assert.assertTrue(jedis.exists(key));
                Thread.sleep(5000);
                Assert.assertFalse(jedis.exists(key));

                jedis.del(key);
        }

        @Test
        @Description("Testing MultiGet Strings")
        public void testMultiGetString() throws InterruptedException {
                jedis.mset(key, value, key1, value1);

                jedis.mget(key, key1);
                Assert.assertTrue(jedis.exists(key));
                Assert.assertTrue(jedis.exists(key1));

                jedis.del(key);
                jedis.del(key1);
        }

}
