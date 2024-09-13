package org.example;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        try (var jedisPool = new JedisPool("127.0.0.1", 6379)) {
            try (Jedis jedis = jedisPool.getResource()) {
                /*
                list
                    1. stack
                    2. queue
                    3. block -
                 */

//                jedis.rpush("stack1", "111");
//                jedis.rpush("stack1", "222");
//                jedis.rpush("stack1", "333");

                List<String> stack1 = jedis.lrange("stack1", 0, -1);
                stack1.forEach(System.out::println);

                System.out.println("----------");

                System.out.println(jedis.rpop("stack1"));
            }
        }
    }
}