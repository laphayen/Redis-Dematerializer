package org.example;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        try (var jedisPool = new JedisPool("127.0.0.1", 6379)) {
            try (Jedis jedis = jedisPool.getResource()) {

                // 1. stack
//                jedis.rpush("stack1", "111");
//                jedis.rpush("stack1", "222");
//                jedis.rpush("stack1", "333");

//                List<String> stack1 = jedis.lrange("stack1", 0, -1);
//                stack1.forEach(System.out::println);

//                System.out.println(jedis.rpop("stack1"));

                // 2. queue
                // 큐에 데이터 삽입 (선입선출 방식)
                jedis.lpush("queue1", "111");
                jedis.lpush("queue1", "222");
                jedis.lpush("queue1", "333");

                // 큐의 모든 데이터 확인
                List<String> queue1 = jedis.lrange("queue1", 0, -1);
                queue1.forEach(System.out::println);

                // 큐에서 데이터 꺼내기
                System.out.println(jedis.rpop("queue1")); // 첫 번째로 삽입된 값 "111" 출력


                // 3. block
                // 블로킹 큐에 데이터 삽입
                jedis.lpush("blockQueue1", "111");
                jedis.lpush("blockQueue1", "222");
                jedis.lpush("blockQueue1", "333");

                // 비동기적으로 데이터를 가져오기
                Thread thread = new Thread(() -> {
                    // 블로킹 팝으로 데이터 가져오기 (타임아웃 0은 무한 대기)
                    List<String> result = jedis.blpop(0, "blockQueue1");
                    System.out.println(result.get(1)); // 첫 번째로 들어온 데이터 출력
                });
                thread.start();

                // 잠시 후 큐에서 데이터를 추가
                Thread.sleep(5000);
                jedis.lpush("blockQueue1", "444");

            }
        }
    }
}