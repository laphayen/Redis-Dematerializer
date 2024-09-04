# Redis CLi

Redis 서버와 상호작용하기 위한 명령줄 도구입니다. Redis 서버에 명령을 보내고, 데이터를 조회하거나 수정할 수 있습니다. Redis 서버와 직접 연결되므로, 실시간으로 명령을 실행하고 결과를 확인할 수 있습니다.


##  Docker 컨테이너 내부에서 Redis CLI를 실행
1. 실행 중인 Redis 컨테이너 확인
먼저, 실행 중인 Redis 컨테이너를 확인하기 위해 다음 명령어를 사용합니다. 여기에서 Redis 컨테이너의 CONTAINER ID 또는 NAMES를 확인합니다.
```
docker ps
```

2. Redis CLI 실행
Redis 컨테이너 내부에서 Redis CLI를 실행하려면, 다음 명령어를 사용합니다:

```
docker exec -it <컨테이너 ID 또는 이름> redis-cli
```

컨테이너 ID가 e35e9a766395인 경우

```
docker exec -it e35e9a766395 redis-cli
```
* * *

### ping
redis-cli ping 명령어는 Redis 서버와의 연결 상태를 확인합니다. Redis 서버가 정상적으로 동작하고 있는지, 연결이 제대로 이루어지고 있는지를 확인할 수 있는 간단한 방법입니다.

```
redis-cli ping
```
기대 결과:
* `PONG`: 서버가 정상적으로 동작하고 있음을 의미합니다. Redis 서버가 응답하고 있다는 뜻입니다.

* `(error) NOAUTH Authentication required.`: Redis 서버가 인증을 요구하고, 인증되지 않은 상태에서 PING 명령어를 실행한 경우 이 오류가 발생할 수 있습니다. 이 경우 먼저 AUTH 명령어를 사용하여 인증해야 합니다.

* * *

### info

INFO 명령어는 Redis 서버의 상태와 성능에 대한 자세한 정보를 제공합니다. Redis 서버의 현재 상태를 모니터링하고, 메모리 사용량, 키 공간, 연결 상태 등의 다양한 메트릭을 확인할 수 있습니다.

```
redis-cli info
```
```
# Server
redis_version:6.2.6
redis_git_sha1:00000000
redis_git_dirty:0
os:Linux 5.4.0-1043-azure x86_64
...

# Memory
used_memory:12345678
used_memory_human:11.77M
used_memory_rss:13516800
...

# Clients
connected_clients:5
client_longest_output_list:0
client_biggest_input_buf:0
...
```

* Server: Redis 서버에 대한 일반 정보 (버전, OS, 빌드 날짜 등).

* Clients: 연결된 클라이언트의 수와 관련 정보.

* Memory: 메모리 사용량 및 관련 통계.

* Persistence: RDB 및 AOF와 관련된 정보 (디스크에 데이터가 저장되는 방식).

* Stats: 일반적인 통계 정보 (명령 처리량, 키스페이스 히트/미스 등).

* Replication: 마스터/슬레이브 복제와 관련된 정보.

* CPU: CPU 사용량 정보.

* Keyspace: 데이터베이스에 저장된 키에 대한 정보 (각 데이터베이스의 키 개수 및 만료된 키 개수 등).



* * *

### --stat
--stat 옵션은 redis-cli 명령어에서 사용되는 옵션 중 하나로, Redis 서버의 실시간 통계를 표시하는 데 사용됩니다. 서버의 다양한 성능 지표를 실시간으로 모니터링할 수 있습니다.

```
redis-cli --stat
```
```
docker exec -it e35e9a766395 redis-cli --stat
------- data ------ --------------------- load -------------------- - child -
keys       mem      clients blocked requests            connections          
0          887.07K  1       0       0 (+0)              1           
0          887.07K  1       0       1 (+0)              1           
0          887.07K  1       0       2 (+1)              1           
0          887.07K  1       0       3 (+1)              1          
```

* Received: Redis 서버가 수신한 총 바이트 수

* Sent: Redis 서버가 전송한 총 바이트 수

* Connections: 현재 연결된 클라이언트 수

* Commands/s: 초당 처리되는 명령 수

* CPU usage: Redis 서버의 CPU 사용량

* Keyspace hits/misses: 키 조회 성공 및 실패 횟수

### redis-benchmark

Redis 서버의 성능을 테스트하는 데 사용됩니다. 여러 시나리오에서 Redis가 명령을 얼마나 빠르게 처리할 수 있는지를 측정할 수 있습니다. 여러 클라이언트가 동시에 명령을 서버에 보내는 상황을 시뮬레이션하여, 부하 상태에서 초당 처리할 수 있는 작업량(throughput)을 측정할 수 있습니다.

```
redis-benchmark
```

```
Summary:
  throughput summary: 328947.38 requests per second
  latency summary (msec):
          avg       min       p50       p95       p99       max
        0.083     0.024     0.087     0.103     0.111     0.311
```
