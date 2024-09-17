package org.example;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        try (var jedisPool = new JedisPool("127.0.0.1", 6379)) {
            try (Jedis jedis = jedisPool.getResource()) {

                // 1. stack
//                jedis.rpush("stack1", "111");
//                jedis.rpush("stack1", "222");
//                jedis.rpush("stack1", "333");
//
//                List<String> stack1 = jedis.lrange("stack1", 0, -1);
//                stack1.forEach(System.out::println);
//
//                System.out.println(jedis.rpop("stack1"));
//                System.out.println(jedis.rpop("stack1"));
//                System.out.println(jedis.rpop("stack1"));
//
//                // 2. queue
//                jedis.lpush("queue1", "111");
//                jedis.lpush("queue1", "222");
//                jedis.lpush("queue1", "333");
//
//                List<String> queue1 = jedis.lrange("queue1", 0, -1);
//                queue1.forEach(System.out::println);
//
//                System.out.println(jedis.rpop("queue1"));
//                System.out.println(jedis.rpop("queue1"));
//                System.out.println(jedis.rpop("queue1"));
//
//
//                // 3. block
//                while (true) {
//                    List<String> blpop = jedis.blpop(10, "queue: blocking");
//                    if (blpop != null) {
//                        blpop.forEach(System.out::println);
//                    }
//                }

                jedis.sadd("users:400:follow", "100", "200", "300");
                jedis.srem("users:400:follow", "100");

                Set<String> smemebers = jedis.smembers("users:500:follow");
                smemebers.forEach(System.out::println);

                System.out.println(jedis.sismember("users:400:follow", "200"));
                System.out.println(jedis.sismember("users:400:follow", "120"));

                System.out.println(jedis.scard("users:400:follow"));

                Set<String> sinter = jedis.sinter("users:400:follow", "users:100:follow");
                sinter.forEach(System.out::println);
            }
        }
    }
}