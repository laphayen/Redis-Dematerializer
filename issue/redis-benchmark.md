## redis-benchmark

redis-benchmark는 Redis 서버에서 실행하는 명령이 아니라, Redis 서버에 연결되지 않은 상태에서 운영 체제의 터미널(명령줄)에서 실행하는 독립적인 성능 테스트 도구입니다. 
Redis CLI(예: 127.0.0.1:6379>) 안에서 실행하려고 하면 ERR unknown command 오류가 발생합니다.

```
(error) ERR unknown command `redis-benchmark`, with args beginning with:
```

## 올바른 사용 방법
Redis CLI(즉, 127.0.0.1:6379>)가 아닌 일반 터미널이나 명령줄에서 redis-benchmark 명령어를 실행해야 합니다.

1. Redis CLI에서 나가기: Redis CLI에서 exit 명령어를 사용하여 CLI를 종료합니다.

```
127.0.0.1:6379> exit
```

2. 터미널에서 redis-benchmark 실행: 일반 터미널로 돌아가서 아래 명령을 입력합니다.

```
redis-benchmark
```

3. 실행 결과

```
Summary:
  throughput summary: 294985.25 requests per second
  latency summary (msec):
          avg       min       p50       p95       p99       max
        0.101     0.024     0.095     0.159     0.415     1.383
```
