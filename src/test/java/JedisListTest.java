import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class JedisListTest extends BaseJedisTest {

        String key = "key";
        String value1 = "Hello";
        String value2 = "World";
        String value3 = "Cesar";

        @Before
        public void before() {
                final List<String> strings = jedis.lrange(key, 0, jedis.llen(key) - 1);
                removeAllElements(strings);
        }

        @Test
        public void testPushList() {

                jedis.lpush(key, value1, value2, value3);

                final List<String> strings = jedis.lrange(key, 0, jedis.llen(key) - 1);

                strings.forEach(s -> {
                        System.out.println(s);
                });

                Assert.assertEquals(3, strings.size());
                Assert.assertEquals(Long.valueOf(3), jedis.llen(key));
        }

        @Test
        public void testSetListByIndex() {

                jedis.lpush(key, value1, value2, value3);
                jedis.lset(key, 0, "Cesar Andres");

                Assert.assertEquals("Cesar Andres", jedis.lindex(key, 0));
        }


        private void removeAllElements(List<String> strings) {
                strings.forEach(s -> {
                        jedis.lrem(key, 0, s);
                });
        }
}
