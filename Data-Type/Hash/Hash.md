
# Redis 사용법: Hashes를 이용한 데이터 저장 및 수정

이 문서는 Redis에서 `HSET`, `HGET`, `HDEL`, `HINCRBY` 등과 같은 명령어를 사용하여 해시(Hash)를 생성하고 데이터를 관리하는 방법을 설명합니다.

## 1. 데이터 생성 및 조회

### HSET: 해시에 값 저장
`HSET` 명령어는 Redis의 해시 데이터 구조에 필드를 설정합니다. 새로운 필드를 추가하거나, 기존 필드의 값을 업데이트할 때 사용합니다.

```bash
127.0.0.1:6379> HSET users:1:info name laphayen email laphayen@gmail.com phone 010-1234-5678
(integer) 3
```
- `users:1:info`라는 해시에 `name`, `email`, `phone` 필드를 추가하고 각각의 값을 설정했습니다.

### HGET: 특정 필드의 값 조회
해시 내에서 특정 필드의 값을 조회할 때는 `HGET` 명령어를 사용합니다.

```bash
127.0.0.1:6379> HGET users:1:info name
"laphayen"
127.0.0.1:6379> HGET users:1:info email
"laphayen@gmail.com"
```

### HGETALL: 해시의 모든 필드와 값 조회
해시의 모든 필드와 값을 확인하려면 `HGETALL` 명령어를 사용합니다.

```bash
127.0.0.1:6379> HGETALL users:1:info
1) "name"
2) "laphayen"
3) "email"
4) "laphayen@gmail.com"
5) "phone"
6) "010-1234-5678"
```

## 2. 데이터 삭제

### HDEL: 특정 필드 삭제
해시에서 특정 필드를 삭제할 때는 `HDEL` 명령어를 사용합니다.

```bash
127.0.0.1:6379> HDEL users:1:info phone
(integer) 1
```
- `phone` 필드가 삭제되었습니다.

### HGETALL: 필드 삭제 후 조회
삭제된 필드를 확인하기 위해 다시 `HGETALL`로 조회하면 `phone` 필드가 없어진 것을 확인할 수 있습니다.

```bash
127.0.0.1:6379> HGETALL users:1:info
1) "name"
2) "laphayen"
3) "email"
4) "laphayen@gmail.com"
```

## 3. 숫자 필드 값 증가

### HINCRBY: 숫자 필드의 값 증가
숫자 값을 가진 필드를 증가시키기 위해서는 `HINCRBY` 명령어를 사용할 수 있습니다.

```bash
127.0.0.1:6379> HSET users:1:info visits 0
(integer) 1
127.0.0.1:6379> HINCRBY users:1:info visits 1
(integer) 1
127.0.0.1:6379> HINCRBY users:1:info visits 10
(integer) 11
127.0.0.1:6379> HINCRBY users:1:info visits 10
(integer) 21
```
- `visits` 필드의 값이 1에서 시작하여 차례대로 1, 11, 21로 증가합니다.

