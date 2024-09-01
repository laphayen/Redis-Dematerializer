# Redis-basic
How to Use Redis and Troubleshooting

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

