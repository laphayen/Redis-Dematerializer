# data-type

Redis는 키-값(Key-Value) 모델을 사용하는 NoSQL 데이터베이스입니다.

## Key

Redis에서 키는 기본적으로 텍스트 형식이지만, 내부적으로는 모든 데이터를 바이너리(binary)로 저장합니다. Redis 키는 텍스트 형식으로 입력되지만, 실제 저장 방식은 바이너리로 처리됩니다.

1. 텍스트 키 (Text Key)

텍스트 키는 사람이 읽을 수 있는 문자열로 정의됩니다. Redis 명령어를 사용할 때 자주 사용하는 일반적인 형태입니다.

```
SET user:1001 "Alice"
```

2. 바이너리 키 (Binary Key)
Redis는 내부적으로 텍스트 키든 값이든 모두 바이너리로 처리합니다. Redis가 8비트로 데이터를 저장하고 처리합니다. 별도의 인코딩 없이 그대로 저장할 수 있습니다. Redis는 텍스트 데이터뿐만 아니라 이미지, 비디오, 압축된 데이터 등의 바이너리 데이터를 저장하는 것도 가능합니다.

```
SET user:data "\x80\x81\x82"
```

## value

### String

문자열은 바이너리 안전(binaries-safe)하여, 어떤 유형의 데이터든 저장할 수 있습니다. (텍스트, JSON, 숫자, 바이너리 데이터 등) 문자열은 최대 512MB까지 저장할 수 있습니다.

1. SET:
키에 문자열 값을 저장합니다.
기존에 값이 있으면 덮어씁니다.
형식: SET key value
예시: SET mykey "Hello, Redis!"

2. GET:
키에 저장된 값을 가져옵니다.
형식: GET key
예시: GET mykey

3. MSET:
여러 키와 값을 한 번에 설정합니다.
형식: MSET key1 value1 key2 value2 ...
예시: MSET key1 "Hello" key2 "Redis"

4. MGET:
여러 키의 값을 한 번에 가져옵니다.
형식: MGET key1 key2 ...
예시: MGET key1 key2

5. INCR / INCRBY:
문자열로 저장된 숫자 값을 1씩 증가시키거나 지정한 만큼 증가시킵니다.
형식:
INCR key
INCRBY key increment
예시:
INCR counter
INCRBY counter 5

6. DECR / DECRBY:
문자열로 저장된 숫자 값을 1씩 감소시키거나 지정한 만큼 감소시킵니다.
형식:
DECR key
DECRBY key decrement
예시:
DECR counter
DECRBY counter 3

7. GETSET:
기존 값을 가져오면서 새로운 값을 설정합니다.
형식: GETSET key new_value
예시: GETSET mykey "New Value"

8. APPEND:
기존 문자열 값에 새로운 문자열을 추가합니다.
형식: APPEND key value
예시: APPEND mykey " Redis!"

9. STRLEN:
문자열의 길이를 반환합니다.
형식: STRLEN key
예시: STRLEN mykey

```
SET mykey "Hello"
GET mykey               # "Hello"
APPEND mykey " Redis!"  # "Hello Redis!"
GET mykey               # "Hello Redis!"
STRLEN mykey            # 11
```