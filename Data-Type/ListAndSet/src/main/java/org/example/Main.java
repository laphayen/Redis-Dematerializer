package org.example;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        try (var jedisPool = new JedisPool("127.0.0.1", 6379)) {
            try (Jedis jedis = jedisPool.getResource()) {

                // 1. stack
                jedis.rpush("stack1", "111");
                jedis.rpush("stack1", "222");
                jedis.rpush("stack1", "333");

                List<String> stack1 = jedis.lrange("stack1", 0, -1);
                stack1.forEach(System.out::println);

                System.out.println(jedis.rpop("stack1"));
                System.out.println(jedis.rpop("stack1"));
                System.out.println(jedis.rpop("stack1"));

                // 2. queue
                jedis.lpush("queue1", "111");
                jedis.lpush("queue1", "222");
                jedis.lpush("queue1", "333");

                List<String> queue1 = jedis.lrange("queue1", 0, -1);
                queue1.forEach(System.out::println);

                System.out.println(jedis.rpop("queue1"));
                System.out.println(jedis.rpop("queue1"));
                System.out.println(jedis.rpop("queue1"));


                // 3. block
                while (true) {
                    List<String> blpop = jedis.blpop(10, "queue: blocking");
                    if (blpop != null) {
                        blpop.forEach(System.out::println);
                    }
                }

            }
        }
    }
}