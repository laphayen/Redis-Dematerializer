# Redis-basic
How to Use Redis and Troubleshooting

Remote Dictionary Server
in-memory, key-value를 지원하는 오픈소스

## Docker 
docker container를 쉽게 다루기 위해서 Docker Desktop을 설치합니다. Docker hub를 통해 퍼블릭 저장소에 업로드되어 있는 공식 Redis images를 사용합니다. 버전 별로 태그가 있습니다. 이 프로젝트는 6.2 버전을 사용합니다.

```
 docker pull redis:6.2 
```

## in-memory database
디스크 스토리지가 아닌 컴퓨터의 주 메모리(RAM)에 저장하고 관리하는 데이터베이스 관리 시스템입니다. 메모리에 데이터를 저장하면 디스크에서 데이터를 접근하는 것보다 훨씬 빠른 데이터 검색 및 처리 속도를 제공합니다.따라서, 밀리초 단위의 빠른 응답 속도를 보장합니다.

메모리에 저장하기 때문에 메모리의 휘발성(Volatility of RAM) 문제가 발생합니다. 시스템이 재시작되거나 전원이 끊겼을 때 메모리에 저장된 데이터가 사라진다는 것을 의미합니다.

	1.	String (문자열):
	    •	Redis에서 가장 기본적인 데이터 타입으로, 바이너리 세이프 문자열을 저장할 수 있습니다. 문자열의 최대 크기는 512MB입니다.
	    •	예를 들어, 간단한 값을 저장할 때 사용됩니다. (SET key value, GET key)
	2.	List (리스트):
	    •	순서가 있는 문자열의 컬렉션입니다. Redis 리스트는 양방향으로 작업이 가능하며, 리스트의 맨 앞 또는 맨 뒤에 요소를 추가하거나 제거할 수 있습니다.
    	•	대기열이나 작업 큐를 구현할 때 유용합니다. (LPUSH key value, LRANGE key start stop)
	3.	Set (집합):
	    •	중복 없는 문자열의 무순서 컬렉션입니다. 집합에서는 요소의 추가, 삭제, 존재 여부 확인이 매우 빠르게 이루어집니다.
	    •	태그 시스템, 유저 그룹 관리 등에 활용됩니다. (SADD key value, SMEMBERS key)
	4.	Sorted Set (정렬된 집합):
	    •	각 요소에 점수(스코어)를 할당하여 점수에 따라 자동으로 정렬되는 집합입니다. 점수는 부동 소수점 값이 될 수 있으며, 동일한 점수를 가진 요소도 허용됩니다.
	    •	리더보드, 순위표 등에 자주 사용됩니다. (ZADD key score value, ZRANGE key start stop WITHSCORES)
	5.	Hash (해시):
	    •	필드와 값의 쌍으로 이루어진 맵(Map) 타입으로, 한 개의 키 아래 여러 필드와 값을 저장할 수 있습니다.
	    •	사용자 프로필이나 객체의 속성을 저장할 때 유용합니다. (HSET key field value, HGET key field)
	6.	Bitmap (비트맵):
	    •	비트 단위로 데이터를 저장하고 조작할 수 있는 특수한 형태의 문자열입니다.
	    •	대규모의 비트 데이터를 처리할 때 효율적입니다. (SETBIT key offset value, GETBIT key offset)
	7.	HyperLogLog:
	    •	고유한 항목의 개수를 매우 적은 메모리로 추정할 수 있는 데이터 구조입니다.
	    •	대용량 데이터에서 유일한 값의 수를 추정할 때 사용됩니다. (PFADD key element, PFCOUNT key)
	8.	Stream (스트림):
	    •	시간 순서대로 정렬된 데이터를 관리하는 데이터 구조입니다. 메시징 큐나 로그 저장 등에 적합합니다.
	    •	실시간 로그 수집이나 이벤트 스토어로 사용됩니다. (XADD stream key value, XREAD COUNT count STREAMS streamname id)
	9.	Geo (지리 정보):
	    •	지리 좌표 (경도, 위도)를 저장하고 다양한 지리적 쿼리를 지원합니다.
	    •	위치 기반 서비스(LBS)에서 위치 데이터를 저장하고 검색할 때 사용됩니다. (GEOADD key longitude latitude member, GEORADIUS key longitude latitude radius unit)

## Persistent on Disk

데이터를 디스크에 영구적으로 저장하는 기능입니다. 메모리의 휘발성으로 인한 시스템이 재시작되거나 전원이 꺼진 경우 데이터가 모두 사라집니다. 이 문제를 해결하기 위해 Redis는 데이터의 영속성을 보장하는 Persistent on Disk를 제공합니다.

### RDB(Snapshot)

주기적으로 메모리의 데이터를 스냅샷으로 디스크에 저장합니다. 설정된 주기 또는 특정 조건에서 자동으로 실행되고, 서버를 재시작할 경우 스냅샷을 통해 데이터를 복구합니다.

> 장점: 데이터 저장이 빈번하지 않아서 성능에 영향을 적게 미칩니다.

1. 백그라운드 작업
* Redis는 스냅샷을 생성 시 메인 프로세스에서 직접 작업을 수행하지 않습니다. fork() 시스템 호출을 사용해서 백그라운드에서 별도의 프로세스를 생성하여 스냅샷을 디스크에 기록합니다. 따라서 스냅샷 생성 과정이 Redis의 실시간 성능에 영향을 적게 미칩니다.

Redis는 주로 싱글 스레드로 동작하지만, 특정 작업은 백그라운드에서 수행됩니다. 이러한 작업은 주로 Redis 서버의 메인 이벤트 루프가 차단되지 않도록 하기 위해서입니다. Redis에서 백그라운드 작업을 수행하는 주요 사례 중 하나는 RDB 스냅샷 생성과 AOF 파일의 재작성입니다.

> RDB 스냅샷 생성 (BGSAVE 명령어)

BGSAVE 명령어는 Redis의 현재 데이터를 디스크에 저장하기 위해 백그라운드에서 RDB 스냅샷을 생성합니다. 이 작업은 Redis 서버의 메인 스레드가 계속해서 클라이언트 요청을 처리할 수 있도록 별도의 자식 프로세스를 생성하여 수행됩니다.

> AOF 파일 재작성 (BGREWRITEAOF 명령어)

BGREWRITEAOF 명령어는 AOF 파일이 너무 커졌을 때, AOF 파일을 재작성하여 크기를 줄이는 작업을 백그라운드에서 수행합니다. 이 역시 자식 프로세스를 통해 실행됩니다.

```
// Redis의 RDB 스냅샷을 백그라운드에서 저장하는 부분
int rdbSaveBackground(char *filename) {
    pid_t childpid;

    if (server.rdb_child_pid != -1) return C_ERR;

    // Redis는 자식 프로세스를 생성하여 RDB 저장 작업을 수행합니다.
    if ((childpid = fork()) == 0) {
        /* 자식 프로세스 */
        if (rdbSave(filename) == C_OK) {
            exit(0); // 성공적으로 완료되면 프로세스를 종료합니다.
        } else {
            exit(1); // 실패 시 종료 코드 1을 반환합니다.
        }
    } else {
        if (childpid == -1) {
            return C_ERR;
        }
        server.rdb_child_pid = childpid;
        return C_OK;
    }
}

// Redis의 AOF 파일을 백그라운드에서 재작성하는 부분
int rewriteAppendOnlyFileBackground(void) {
    pid_t childpid;

    if (server.aof_child_pid != -1) return C_ERR;

    // Redis는 자식 프로세스를 생성하여 AOF 재작성 작업을 수행합니다.
    if ((childpid = fork()) == 0) {
        /* 자식 프로세스 */
        if (rewriteAppendOnlyFile(server.aof_filename) == C_OK) {
            exit(0); // 성공적으로 완료되면 프로세스를 종료합니다.
        } else {
            exit(1); // 실패 시 종료 코드 1을 반환합니다.
        }
    } else {
        if (childpid == -1) {
            return C_ERR;
        }
        server.aof_child_pid = childpid;
        return C_OK;
    }
}
```

2. 주기적 스냅샷
* 주기적 또는 특정 조건에서만 생성합니다. 설정은 스냅샷 생성 빈도를 조절함으로써 디스크 I/O를 줄이고, CPU와 메모리 사용량을 관리합니다. 

> 단점: 마지막 스냅샷 이후의 데이터는 손실될 수 있습니다.

## Single Thread

Redis는 single-threaded 아키텍처를 채택하고 있습니다. 기본적으로 하나의 스레드에서 모든 클라이언트 요청을 처리합니다.

1. 단순성과 예측 가능성
* 코드의 복잡성을 줄입니다. 멀티스레딩 환경의 스레드 간의 동기화 문제, 경쟁 상태, 데드락 등의 복잡한 문제가 발생하지 않습니다.

2. 빠른 성능
* CPU 바운드보다는 주로 메모리 바운드 작업을 수행합니다. 대부분의 작업이 메모리에서 데이터를 읽고 쓰는 것이며, 이러한 작업은 매우 빠릅니다. 또한, Redis의 이벤트 루프(Event Loop)는 비동기 I/O 모델을 사용하여, 네트워크와 디스크 I/O에서 대기 시간을 최소화하며, 요청을 매우 효율적으로 처리합니다.

3. 동시성 제어
* 싱글 스레드 환경에서는 한 번에 하나의 명령만 실행되므로, 명령이 실행되는 동안 다른 명령이 끼어들 수 없습니다. 이는 Redis가 내부적으로 동시성을 관리할 필요가 없음을 의미하며, 데이터 일관성을 유지하는 것이 훨씬 쉬워집니다. 데이터 구조에 대한 동시 접근 시 데이터가 손상될 위험이 없기 때문에, 복잡한 잠금 메커니즘 없이도 높은 일관성을 보장할 수 있습니다.

4. 메모리 관리
* 메모리 할당 및 해제와 같은 작업이 매우 효율적으로 이루어집니다. 여러 스레드에서 메모리 작업을 수행할 때 발생할 수 있는 잠금 비용이나 메모리 파편화 문제를 피할 수 있습니다.

5. 싱글 스레드의 성능확장
* I/O 멀티플렉싱: Redis는 내부적으로 epoll, kqueue, select와 같은 I/O 멀티플렉싱 기술을 사용하여 다수의 클라이언트 연결을 효율적으로 처리합니다. 이를 통해 하나의 스레드에서 수천 개의 클라이언트 요청을 매우 빠르게 처리할 수 있습니다.
* 파이프라이닝: 클라이언트는 여러 명령을 한 번에 보내고 응답을 한 번에 받을 수 있습니다. 이를 통해 네트워크 지연 시간을 줄이고 처리량을 높일 수 있습니다.
* Redis Cluster: Redis는 클러스터링을 통해 여러 인스턴스에 데이터를 분산시키고, 수평적으로 확장할 수 있습니다. 이 방식으로 성능을 확장할 수 있습니다.