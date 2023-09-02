# 01. 함수 관점에서 동기와 비동기의 차이

## 동기와 비동기란?
### 함수 호출 관점
- Caller : 호출 하는 함수
- Callee : 호출 당하는 함수
  (Callee 가 Caller 를 call 했다.)
- 함수형 인터페이스 : 1개의 추상메서드만 갖고있는 인터페이스
  - 함수를 일급객체로 사용할 수 있다. (함수를 변수에 할당하거나 인자로 전달하고 반환값으로 사용 가능하다.)
  - 함수형 인터페이스는 호출한 쓰레드에서 실행된다.
- 동기 : caller는 callee의 결과에 관심이 있다. (caller는 결과를 이용해서 action을 수행한다.)
- 비동기 : caller는 callee의 결과에 관심이 없다. (callee는 결과를 이용해서 callback을 수행한다.)

## Blocking과 Non-Blocking이란?
### 함수 호출 관점
- Blocking : callee 를 호출한 후, callee가 완료되기 전까지 caller가 아무것도 할 수 없다.
  - 제어권을 callee가 가지고있다.
  - caller는 caller가 완료될때까지 대기한다.
  - caller와 다른 별도의 thread가 필요하지 않다. (혹은 thread를 추가로 쓸수도 있다.)
    - caller, callee 가 동일한 thread에서 발생할 가능성이 높다.
- Non-Blocking : callee 를 호출한 후, callee가 완료되지 않더라도 caller는 본인의 일을 ㅎ라 수 있다.
  - 제어권을 caller가 가지고있다.
  - caller는 callee를 기다리지 않고 본인의 일을 한다.
  - caller와 다른 별도의 thread가 필요하다.
    - caller가 자신의 일을 하면서 callee도 다른 일을 해야하기 때문에 별도의 쓰레드로 진행된다.

## 동기, 비동기 Blocking, Non-Blocking
- 동기 & Blocking
  - caller는 아무것도 할 수 없는 상태가 된다. (Blocking)
  - 결과를 얻은 후 직접 처리한다.(동기)
- 비동기 & Blocking
  - caller는 아무것도 할 수 없는 상태가 된다. (Blocking)
  - 결과는 callee가 처리한다. (비동기)
- 동기 & Non-Blocking
  - caller는 자기 할일을 할 수 있다.  (Non-Blocking)
  - 결과를 얻은 후 직접 처리한다. (동기)
- 비동기 & Non-Blocking
  - caller는 자기 할일을 할 수 있다. (Non-Blocking)
  - 결과는 callee가 처리한다. (비동기)

## I/O 관점에서 Blocking, Non-Blocking
- Blocking의 종류
  - blocking은 thread가 오랜 시간 일을 하거나 대기하는 경우 발생한다.
    - CPU-bound blocking : 오랜시간 일을한다.
      - thread가 대부분의 시간 CPU 점유
      - 연산이 많은 경우
      - 추가적인 코어를 투입 (리소스를 더 넣어서 성능을 빠르게 하거나 시간을 단축함)
    - IO-bound blocking : 오랜시간 대기한다.
      - thread가 대부분의 시간을 대기
      - 파일 읽기/쓰기, nework 요청 처리, 요청 전달 등 
      - 실제로 일을 하기보다는 다시 돌아오기까지의 시간
      - IO-bound non-blocking 가능 
- Blocking의 전파
  - 하나의 함수에서 여러 함수를 호출하고, 함수 호출은 중첩적으로 발생한다.
  - callee는 caller가 되고, 다시 다른 callee를 호출
  - blocking한 함수를 하나라도 호출한다면 caller는 blocking이 된다.
  - 함수가 non-blocking하려면 모든 함수가 non-blocking이어야 한다.
    - I/O bound blocking 또한 발생하면 안된다.
- 동기 Blocking I/O
  - recvfrom을 호출
  - blocking socket을 이용해서 read/write 수행
  - 쓰레드가 block된다 (wait queue에서 기다린다)
- 동기 Non-Blocking I/O
  - recvfrom을 주기적으로 호출
  - non-blocking socket을 이용해서 read/write 수행
  - 작업이 완료되지 않았다면 에러 반환
  - 결과를 기다리는 동안 추가적인 일을 할 수 있다.
- 비동기 Non-blocking I/O
  - aio-read를 호출
  - 작업이 완료되면 Kernel은 signal을 보내거나 callback을 호출한다.