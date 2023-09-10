# 01. Reactive streams 의 역사
- Netflix RxJava

# 02. Reactive manifesto
- Reactive란?
  - 무언가를 바꾸거나 예방하기 위해 먼저 행동하기 보다는 사건이나 상황에 반응한다.
  - 외부에서 요청을 보냈을때 이 요청에 반응하는 것
- Reactive manifesto
  - 소프트웨어 아키텍쳐에 대한 선언문
  - Reactive system의 특성을 강조하고 구축에 필요한 가이드라인 제공
  - 4가지의 핵심 가치를 제시
    - Responsive (응답성)
    - Elastic (유연성)
    - Resilient (탄력성, 복원력)
    - Message Driven (메시지 기반)
  - Reactive System 안에 모든 구성요소들이 reactive 해야 전체가 reactive 하다.
  - 정리
    - 핵심 가치 : 가능한 한 즉각적으로 응답
    - 첫번째 형태 1: 장애에 직면하더라도 응답성을 유지
    - 두번째 형태 : 작업량이 변화하더라도 응답성을 유지
    - 방법 : 비동기 non-blocking 기반의 메시지 큐를 사용해 통신한다.

# 03. Reactive programming 
- Reactive stream
  - callee는 caller에게 응답이 아닌 publisher을 제공한다.
  - callee는 각각의 값을 publisher를 통해서 하나씩 전달한다.
  - caller는 해당 publisher를 subscribe하거나 다른 caller에게 전달한다.
  - caller는 subscriber를 등록하여 back-pressure를 조절하여 처리가능한 만큼만 전달받는다.
- 구성요소
  - 서로 비동기적으로 메시지를 주고받으며, 독립적인 실행을 보장한다.
    - callee는 publisher를 반환하고, caller는 subscriber를 등록한다.
    - 이 과정에서 caller와 callee는 비동기적으로 동작한다.
  - 메시지 큐를 생성하고 배압을 적용하여 부하를 관리하고 흐름을 제어한다.
    - publisher는 메시지 큐를 생성해서 부하를 관리하고 흐름을 제어한다.
    - back-pressure를 조절할 수 있는 수단을 제공한다.
- Reactive stream 특징
  - 비동기 데이터 stream을 사용하는 패러다임
  - 모든 것이 이벤트로 구성되고 이벤트를 통해서 전파되어야 한다.
    - event-driven
    - 데이터의 전달, 에러, 완료까지 모든 이벤트로 취급
  - Reactive manifesot의 Responsive, Resilient, Elastic, Message-Driven 까지 모두 해당 
- Reactive Prograaming에서의 event-driven
  - message-driven vs event-driven
    - message : event, command, query 등 다양한 형태를 수용
    - message-driven은 event, command, query 등 다양한 형태의 메시지를 비동기적으로, 가능하다면 배압을 적용해서 전달하는 형태에 집중
    - event-driven은 message의 형태를 event로 제한
    - completion, error 심지어 값 까지도 이벤트의 형태로 전달 
    - 따라서 reactive programming은 reactive manifesot의 원칙을 지킨다.
- 