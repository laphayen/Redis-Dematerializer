# 자바 환경

자바환경에서 Redis를 사용하는 설정입니다.

1. Gradle 프로젝트 설정

build.gradle에 Jedis 의존성을 추가합니다.

```
implementation 'redis.clients:jedis:5.1.2'
```

2. Redis 사용 코드 작성

src/main/java 디렉터리 아래에 Redis를 사용하는 자바 클래스에 작성합니다.
```
import redis.clients.jedis.Jedis;

public class RedisExample {
    public static void main(String[] args) {
        // Redis 서버에 연결
        Jedis jedis = new Jedis("localhost", 6379);

        // Redis 연결 확인
        System.out.println("Connection to Redis server successfully");

        // 연결 종료
        jedis.close();
    }
}
```
