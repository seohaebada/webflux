# 01. Future 인터페이스
## CompletableFuture 클래스
- Future
  - 비동기적인 작업 수행
  - 해당 작업이 완료되면 결과를 반환하는 인터페이스
- CompletionStage
  - 비동기적인 작업 수행
  - 해당 작업이 완료되면 결과를 처리하거나 다른 CompletionStage를 연결하는 인터페이스

## Future 인터페이스
- isDone() : task가 완료되었다면 원인과 상관없이 true를 반환
  - cancel이 되어도 true (실행중이 아니라면 모두 true)
- isCancelled() : task가 명시적으로 취소된 경우, true를 반환
- get() : 결과를 구할때까지 thread가 계속 block
  - future에서 무한루프나 오랜시간이 걸린다면 thread가 blocking 상태를 유지
  - get(long timeout, TimeUnit unit) : 결과를 구할때까지 timeout 동안 thread가 블록
    - timeout이 넘어가도 응답이 반환되지 않으면 TImeoutException 발생
- cancel() : future의 작업 실행 취소, 취소할 수 없다면 false 반환, mayInterruptIfRunning가 false라면 시작하지 않은 작업에 대해서만 취소
- 한계
  - cancel을 제외하고 외부에서 future을 컨트롤 할 수 없다.
  - 반환된 결과를 get()해서 접근하기 때문에 비동기 처리가 어렵다.
  - 완료되거나 에러가 발생했는지 구분하기 어렵다. 
    - cancel(true) 후에도 isDone()이 true다.
    - getFutureWithException() 후에도 isDone()이 true다.

## ExecutorService
- 쓰레드 풀을 이용하여 비동기적으로 작업을 실행하고 관리
- 별도의 쓰레드(직접 쓰레드를 만들 필요 없이 알아서 관리)를 생성하고 관리하지 않아도 되므로, 코드를 간결하게 유지 가능
- 쓰레드 풀을 이용하여 자원을 효율적으로 관리
- execute() : Runnable 인터페이스를 구현한 작업을 쓰레드풀에서 비동기적으로 실행
- submit() : Callable 인터페이스를 구현한 작업을 쓰레드 풀에서 비동기적으로 실행하고, 해당 작업의 결과를 Future<T> 객체로 반환
- shutdown() : ExecutorService를 종료. 더이상 task를 받지않는다.
  - 쓰레드풀을 모두 사용하면 shutdown() 해야한다. 안그럼 무한정 대기하게된다. (main 쓰레드에서)
  - shutdown을 하는순간 더이상 task가 추가되지 않고, 진행중인 작업을 기다리지 않는다.
  - 기다리려면 await() 등을 호출해야한다.

## ExecutorService 생성
- newSingleThreadExceutor : 단일 스레드로 구성된 스레드 풀을 생성. 한번에 하나의 작업만 실행
- newFixedThreadPool : 고정된 크기의 쓰레드 풀을 생성. 크기는 인자로 주어진 n과 동일
- newCachedThreadPool : 사용가능한 쓰레드가 없다면 새로 생성해서 작업을 처리하고, 있다면 재사용. 쓰레드가 일정 시간 사용되지 않으면 회수
- newScheduledThreadPool : 스케줄링 기능을 갖춘 고정 크기의 쓰레드 풀 생성. 주기적이거나 지연이 발생하는 작업을 실행
- newWorkStealingPool : work steal 알고리즘을 사용하는 ForkJoinPool을 생성

# 02. CompletionStage 인터페이스