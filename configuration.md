# Redis Configuration

Redis의 환경을 구성하는 방법입니다. Docker Image의 Redis 6.2를 기준으로 합니다. 버전에 따라 구성 방법이 다를 수 있습니다.

### Docker Images

Docker Hub의 공식 Redis 이미지를 가져옵니다. 6.2 버전을 사용하기 위해 명령어에 버전을 표시합니다.

```
docker pull redis:6.2
```

### Docker images

Docker에서 로컬 시스템에 저장된 모든 이미지를 나열합니다.
```
docker images
```
```
REPOSITORY                                  TAG       IMAGE ID       CREATED         SIZE
laphayen/pharmacy-recommendation-app        latest    862e524c3146   2 months ago    564MB
```


가져온 이미지는 다음 명령어를 통해 실행합니다.
```
docker run --rm -it -p 6379:6379 redis:6.2
```
* docker run: 새로운 컨테이너를 생성하고 실행하는 명령어입니다.
* --rm: 컨테이너가 종료되면 자동으로 삭제되도록 설정합니다. 이 옵션이 없으면 컨테이너는 종료된 후에도 시스템에 남아 있습니다.
* -it: 두 개의 옵션이 결합된 형태입니다.
* -i는 대화형 모드를 활성화하여 컨테이너의 표준 입력을 계속 열어 둡니다.
* -t는 가상 터미널을 할당하여 쉘 세션을 사용할 수 있게 합니다.
* -p 6379:6379: 호스트의 포트 6379를 컨테이너의 포트 6379에 매핑합니다. Redis는 기본적으로 6379 포트에서 동작하므로, 이 설정을 통해 호스트 머신에서 컨테이너의 Redis 인스턴스에 접근할 수 있습니다.
* redis:6.2: Redis 버전 6.2의 Docker 이미지를 사용하여 컨테이너를 실행합니다. Docker Hub에서 해당 버전의 이미지를 자동으로 다운로드하고 실행합니다.

### Docker container의 port

Docker 컨테이너의 포트를 호스트 운영 체제의 포트로 전달하는 것은 -p 또는 --publish 옵션을 사용하여 설정할 수 있습니다. 이를 통해 호스트 시스템이 컨테이너 내부에서 실행 중인 애플리케이션에 접근할 수 있습니다.


### 포트 전달 설정 예시
```
docker run -p <호스트 포트>:<컨테이너 포트> <이미지 이름>
```


### Redis 컨테이너 포트 전달

Redis 컨테이너에서 기본 포트인 6379를 호스트의 포트 6379에 연결하려면 다음과 같이 실행할 수 있습니다:

```
docker run -d -p 6379:6379 redis
```

-d: 컨테이너를 백그라운드에서 실행합니다.
-p 6379:6379: 호스트의 포트 6379를 컨테이너의 포트 6379에 매핑합니다.
redis: Redis 이미지를 사용하여 컨테이너를 생성합니다.

### Docker Container 종료

1. 실행 중인 컨테이너 확인

먼저 실행 중인 컨테이너의 ID나 이름을 확인해야 합니다. 여기에서 Redis 컨테이너의 CONTAINER ID 또는 NAMES 값을 찾을 수 있습니다.
```
docker ps
```

2. 컨테이너 종료
컨테이너를 종료하려면 docker stop 명령어를 사용합니다. 컨테이너의 ID 또는 이름을 이용해 실행합니다.


docker stop <컨테이너 ID 또는 이름> 또는 컨테이너 이름이 my-redis-container인 경우:

```
docker stop 86a83abb2a59
```
```
docker stop redis:6.2
```

3. 컨테이너 강제 종료 (옵션)
만약 컨테이너가 정상적으로 종료되지 않는 경우, docker kill 명령어를 사용해 강제 종료할 수 있습니다:

```
docker kill <컨테이너 ID 또는 이름>
```
