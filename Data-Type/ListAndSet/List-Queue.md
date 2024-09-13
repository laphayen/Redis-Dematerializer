
# Redis 리스트 큐 관리

Redis 명령어를 사용하여 큐를 관리합니다. `RPUSH`, `LRANGE`, `LPOP`, `LTRIM` 등의 명령어를 사용합니다.

## Redis 명령어 설명

### `RPUSH queue1 value`
- `RPUSH`는 리스트 `queue1`의 오른쪽에 새로운 값을 추가합니다. 이 명령은 `queue1`에 100, 200, 300, 400을 차례대로 추가합니다.
  ```bash
  RPUSH queue1 100
  RPUSH queue1 200
  RPUSH queue1 300
  RPUSH queue1 400
  ```
  

### `LRANGE queue1 0 -1`
- `LRANGE`는 리스트의 지정된 범위의 요소들을 반환합니다. `0`은 첫 번째 요소를 의미하며, `-1`은 마지막 요소를 의미합니다.

  ```bash
  LRANGE queue1 0 -1
  ```
  리스트 `queue1`의 모든 요소를 반환합니다.

### `LPOP queue1`
- `LPOP`은 리스트의 첫 번째 요소를 반환하고, 그 요소를 리스트에서 제거합니다.

  ```bash
  LPOP queue1
  ```
  리스트의 첫 번째 요소(100)를 반환하고 제거합니다.

### `LTRIM queue1 start stop`
- `LTRIM`은 지정된 범위 외의 요소들을 제거하여 리스트를 자릅니다. 범위는 `start`와 `stop` 인덱스로 지정합니다.

  ```bash
  LTRIM queue1 0 2
  ```
  리스트 `queue1`을 첫 번째부터 세 번째 요소까지만 남기고 나머지는 모두 제거합니다.

## 결과
마지막 `LTRIM` 후 리스트 `queue1`의 상태는 다음과 같습니다:
```bash
1) "100"
2) "200"
3) "300"
```

리스트의 앞쪽 요소들만 유지하고 나머지를 자르는 방식으로 리스트가 계속해서 관리되는 것을 확인할 수 있습니다.
