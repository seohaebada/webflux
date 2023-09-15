# 01. Selector 소개
## Java NIO non-blocking의 문제점
- main 쓰레드에서 accept 완료되었는지 주기적으로 확인
- 각각의 쓰레드에서 read 가능한지 주기적으로 확인
- 채널 상태를 수동으로 관리, 코드 복잡성 증가
- 동시에 발생하는 요청이 증가하는 경우, 연결 처리가 순차적으로 발생하여 성능 감소 

## busy-wait
- 동기 non-blocking 에서 주로 발생
- caller가 방해받지 않고 본인의 일을 할 수 있지만 결과를 알아야하기 때문에 계속 주기적으로 묻는다.
- 루프를 이용해서 원하는 자원을 얻을때까지 확인
- 지속적으로 cpu를 점유하기 때문에 cpu 자원이 낭비
- 확인하는 주기에 따라서 응답 시간 지연이 발생 

## SelectableChannel
- configureBlocking과 register 함수 제공
- register : Selector에 channel 을 등록 가능 

## Selector
- java.nio.channels 패키지
- 여러 Channel의 이벤트를 등록하고 준비된 이벤트를 모아서 조회 가능
- select와 selectedKeys 메서드 제공

# 02. Selector 사용해서 소켓 서버 고도화

# 03. epoll 소개

# 04. Reactor pattern 소개
- accept, read, write 이벤트들을 한곳에 등록하여 관찰하고, 준비 완료된 이벤트들을 request handler에게 전달한다.
- Reactor
  - 별도의 쓰레드에서 실행
    - Runnable을 구현하고 별도의 쓰레드에서 run 실행 
  - 여러 요청의 이벤트를 등록하고 감시하며
    - Selector 사용
  - 이벤트가 준비되면 disaptch 한다.
    - EventHandler 인터페이스를 만들고 call 
- Handler: Reactor로부터 이벤트를 받아서 처리한다.
- EventHandler 구현
  - Reactor로부터 이벤트를 받아서 처리
  - 해당 처리가 REactor에 영향을 주지 않아야 하므로 별도 쓰레드로 실행
- 구성 : Reactor, Selector, Acceptor, EventHandler(이 구현체는 read 이벤트에만 집중)

# 05. Reactor pattern 사용해서 http 서버 구현

# 06. Reactor pattern 정리