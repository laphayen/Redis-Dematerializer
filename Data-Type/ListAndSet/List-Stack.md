
# Redis 리스트 스택 관리

Redis 명령어를 사용하여 리스트를 스택처럼 관리합니다. `RPUSH`와 `RPOP` 명령어가 사용합니다. 스택은 **LIFO (Last In, First Out)** 방식으로 동작하므로, 나중에 삽입된 요소가 먼저 삭제됩니다.

## 명령어 설명

### `RPUSH stack1 value`
- `RPUSH`는 리스트 `stack1`의 오른쪽 끝에 새로운 값을 추가합니다.

  ```bash
  RPUSH stack1 100
  RPUSH stack1 100
  RPUSH stack1 100
  RPUSH stack1 200
  ```
  이 명령은 `stack1`에 100을 세 번, 200을 한 번 차례대로 추가합니다.

### `RPOP stack1`
- `RPOP`은 리스트 `stack1`의 오른쪽 끝에서 요소를 꺼내고, 그 요소를 반환합니다. 스택의 `POP` 연산과 동일한 역할을 합니다.

  ```bash
  RPOP stack1
  ```
  이 명령은 리스트의 마지막 요소를 반환하고, 그 요소를 리스트에서 제거합니다.

## 실행 과정

1. `RPUSH` 명령을 사용하여 `stack1`에 순서대로 100, 100, 100, 200을 삽입합니다.
2. `RPOP` 명령을 사용하여 가장 마지막에 삽입된 값인 200부터 하나씩 꺼냅니다.
3. 200을 꺼낸 후, 100을 세 번 더 꺼냅니다.
4. 리스트가 비게 되면 `RPOP` 명령은 `nil`을 반환합니다.

## 최종 결과

마지막으로 삽입된 값부터 먼저 제거되는 스택의 LIFO 구조를 잘 보여줍니다.

```bash
RPUSH stack1 100     (리스트에 100 추가)
RPUSH stack1 100     (리스트에 100 추가)
RPUSH stack1 100     (리스트에 100 추가)
RPUSH stack1 200     (리스트에 200 추가)

RPOP stack1          (200 반환, 제거)
RPOP stack1          (100 반환, 제거)
RPOP stack1          (100 반환, 제거)
RPOP stack1          (100 반환, 제거)
RPOP stack1          (nil 반환, 리스트가 비어 있음)
```
