# 01. JAVA IO - InputStream
# 02. JAVA IO - OutputStream
## 함수 호출 모델
- 동기, Blocking : JAVA IO
- 동기, Non-blocking : JAVA NIO (File IO는 non-blocking 불가능)
- 비동기, Non-blocking : JAVA AIO

## I/O 모델
- 동기, Blocking : JAVA IO
- 동기, Non-blocking : JAVA NIO, JAVA AIO

## JAVA IO
- Java1.0에서 처음 도입
- 파일과 네트워크에 데이터를 읽고 쓸수있는 API 제공
- byte 단위로 읽고 쓸 수 있는 stream(InputStream, OutputStream)
- blocking 으로 동작

## JAVA IO Reader, Writer
- Java 1.1에서 도입
- character 단위로 읽고 쓸 수 있는 stream(Reader, Writer)
- 문자 인코딩 지원
- blocking 으로 동작

## 한계
- 동기 blocking 으로 동작
  - application이 read를 호출하면 kernel이 응답을 돌려줄때까지 아무것도 할 수 없다.
  - blocking 해결을 위해 I/O 요청이 발생할때마다 쓰레드를 새로 할당하면, 쓰레드를 생성 및 관리하는 비용과 컨텍스트 스위칭으로 인한 cpu 자원 소모
- 커널 버퍼에 직접 접근 불가. 따라서 메모리 copy가 발생
  - 커널 메모리가 존재하는데, 값을 읽어들이고 쓴다고 하면 DMA(Direct Memory Access) Copy를 통해서 커널 버퍼에 값을 복사한다.
  - Buffer에 직접 접근이 불가능하고 커널 버퍼에서 jvm 버퍼로 복사한다. (cpu 자원 소모)
  - jvm 버퍼, jvm 메모리에 있기 때문에 gc 대상이 되어 cpu 자원 소모

# 03. JAVA NIO - Buffer
## Java NIO
- New Input/Output
- java 1.4에서 처음 도입
- buffer 기반
- 파일과 네트워크에 데이터를 읽고 쓸 수 있는 API 제공
- non-blocking 지원 
- selector, channel 도입으로 높은 성능 보장

## Java NIO vs Java IO
- 데이터의 흐름   = `양방향` vs `단방향`
- 종류          = `Channel` vs `InputStream, OutputStream`
- 데이터의 단위   = `buffer` vs `byte 혹은 character`
- blocking 여부 = `non-blocking 지원` vs `blocking만 가능`
- 특이사항       = `Selector 지원` vs ''

## Channel 과 Buffer
- 데이터 읽을때
  - 적절한 크기의 Buffer를 생성하고 Channel의 read() 메서드를 사용하여 데이터를 Buffer에 저장
- 데이터 쓸때
  - 먼저 Buffer에 데이터를 저장하고 Channel의 write() 메서드를 사용하여 목적지로 전달
- clear() 메서드로 초기화하여 다시 사용 가능 

## Buffer의 위치 속성
- capacity
- position
- limit
- 0 <= mark <= position <= limit <= capacity

## buffer의 위치 속성
- flip
- rewind
- clear

# 04. JAVA NIO - DirectByteBuffer와 Channel
## DirectByteBuffer
- native 메모리에 저장
- 커널 메모리에서 복사를 하지 않으므로 데이터 읽고 쓰는 속도가 빠르다
- 비용이 많이드는 system call을 사용 - allocate, deallocate 가 느리다

## HeapByteBuffer
- Java Heap 메모리에 저장 (byte array를 랩핑)
- 커널 메모리에서 복사가 일어나므로 데이터 읽고 쓰는 속도가 느리다.
- 임시로 Direct Buffer 만드므로 성능 저하
- gc에서 관리되므로 allocate, deallocate 가 빠르다

## SelectableChannel 
- configureBlocking, register 함수 제공
- configureBlocking : serverSocketChannel의 accept, socketChannel의 connect 등이 non-blocking으로 동작한다.

## FileChannel
- SelectableChannel을 상속하지 않는 FileChannel은 blocking 동작만 가능 

# 05. JAVA IO, NIO 사용해서 소켓 서버 구현
- `ch07_java_io_nio/src/main/java/com/example07/_실습` 패키지 참고 

# 06. Java AIO (NIO2)
## Java AIO
- Thread pool과 epoll, kqueue 등의 이벤트 알림 system call 을 이용
- I/O 준비되었을때, Future 혹은 callback으로 비동기적인 로직 처리 가능 