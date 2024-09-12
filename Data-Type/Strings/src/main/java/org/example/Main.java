package org.example;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        try (var jedisPool = new JedisPool("localhost", 6379)) {
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.set("users:300:email", "laphayen@gmail.com");
                jedis.set("users:300:name", "laphayen");
                jedis.set("users:300:age", "100");

                var userEmail = jedis.get("users:300:email");
                System.out.println(userEmail);

                List<String> userInfo =  jedis.mget("users:300:email", "users:300:name", "users:300:age");
                userInfo.forEach(System.out::println);

                long counter = jedis.incr("counter");
                System.out.println(counter);

                counter = jedis.incrBy("counter", 10L);
                System.out.println(counter);

                counter = jedis.decr("counter");
                System.out.println(counter);

                counter = jedis.decrBy("counter", 20L);
                System.out.println(counter);

                Pipeline pipeline = jedis.pipelined();
                pipeline.set("users:400:email", "laphayen@gmail.com");
                pipeline.set("users:400:name", "laphayen");
                pipeline.set("users:400:age", "200");

                List<Object> objects = pipeline.syncAndReturnAll();
                objects.forEach(i -> System.out.println(i.toString()));
            }

        }

    }
}