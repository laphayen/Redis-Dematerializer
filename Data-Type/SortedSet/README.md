
# Sorted Set을 이용한 데이터 저장 및 수정

이 문서는 Redis에서 `ZADD`, `ZRANGE`, `ZREM`, `ZINCRBY` 명령어를 사용하여 Sorted Set(정렬된 집합) 데이터를 관리하는 방법을 설명합니다.

## 1. 데이터 추가 및 조회

### ZADD: Sorted Set에 값 추가
`ZADD` 명령어는 Redis의 Sorted Set에 요소를 추가합니다. 각 요소는 점수에 따라 정렬됩니다.

```bash
127.0.0.1:6379> ZADD "game1:scores" 100 user1 200 user2 300 user3
(integer) 3
```
- `game1:scores`라는 Sorted Set에 `user1`, `user2`, `user3`와 그들의 점수를 추가합니다.

```bash
127.0.0.1:6379> ZADD "game1:scores" 50 user4 150 user5 350 user6
(integer) 3
```
- 추가적으로 `user4`, `user5`, `user6`와 그들의 점수를 삽입합니다.

### ZRANGE: 점수 순으로 요소 조회
`ZRANGE` 명령어는 Sorted Set에서 요소들을 점수 순으로 조회합니다.

```bash
127.0.0.1:6379> ZRANGE "game1:scores" 0 +inf BYSCORE LIMIT 0 10
1) "user4"
2) "user1"
3) "user5"
4) "user2"
5) "user3"
6) "user6"
```
- `game1:scores`에서 점수가 낮은 순으로 상위 10개의 요소를 조회합니다.

### ZRANGE WITHSCORES: 점수와 함께 요소 조회
`ZRANGE`에 `WITHSCORES` 옵션을 추가하여 각 요소의 점수도 함께 조회할 수 있습니다.

```bash
127.0.0.1:6379> ZRANGE "game1:scores" 0 +inf BYSCORE LIMIT 0 10 WITHSCORES
 1) "user4"
 2) "50"
 3) "user1"
 4) "100"
 5) "user5"
 6) "150"
 7) "user2"
 8) "200"
 9) "user3"
10) "300"
11) "user6"
12) "350"
```

## 2. 데이터 삭제

### ZREM: 특정 요소 삭제
`ZREM` 명령어를 사용하여 Sorted Set에서 특정 요소를 삭제할 수 있습니다.

```bash
127.0.0.1:6379> ZREM "game1:scores" user3
(integer) 1
```
- `user3`가 삭제되었습니다.

```bash
127.0.0.1:6379> ZREM "game1:scores" user3333
(integer) 0
```
- 존재하지 않는 `user3333`을 삭제하려 할 때는 아무 것도 삭제되지 않습니다.

## 3. 점수 기반 조회 및 수정

### ZRANGE (BYSCORE REV): 점수 내림차순 조회
`ZRANGE` 명령어에 `REV` 옵션을 추가하면 점수를 기준으로 내림차순으로 조회할 수 있습니다.

```bash
127.0.0.1:6379> ZRANGE "game1:scores" +inf 0 BYSCORE REV LIMIT 0 3 WITHSCORES
1) "user6"
2) "350"
3) "user2"
4) "200"
5) "user5"
6) "150"
```

### ZINCRBY: 특정 요소의 점수 증가
`ZINCRBY` 명령어를 사용하여 특정 요소의 점수를 증가시킬 수 있습니다.

```bash
127.0.0.1:6379> ZINCRBY "game1:scores" 500 user4
"550"
```
- `user4`의 점수가 50에서 550으로 증가했습니다.

## 4. Sorted Set 크기 조회

### ZCARD: 요소 개수 조회
`ZCARD` 명령어를 사용하여 Sorted Set에 포함된 요소의 개수를 확인할 수 있습니다.

```bash
127.0.0.1:6379> ZCARD "game1:scores"
(integer) 5
```
- `game1:scores`에 5개의 요소가 포함되어 있음을 알 수 있습니다.

