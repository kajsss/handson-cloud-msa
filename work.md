작업내용
============
## 3/30 화요일 

- Gateway 프로젝트 생성 (포트 7000)
- Eureka 서버 프로젝트 생성 (포트 8761)
- Application 이름 셋팅
    - GATEWAY
    - MONOLITHIC

- GATEWAY 라우팅 설정 : /** 에 대해서 MONOLITHIC 서비스로 라우팅 되도록 설정
- 서비스 등록 상태 확인 Eureka Dashboard : http://localhost:8761/


## 3/31 수요일

- Front-End 에서 gateway 7000 번으로 호출하도록 변경 
- Cors 문제가 발생해서, 기존 Back-end 프로젝트에서 설정 제거하고, GateWay에서 Cors 셋팅수행 


## 4/1 목요일
- Docker Compose AD Mounted Volume 수정 
- AD SErvie Eureka Dependency 추가 
- Ad Appliacation에 @EnableEurekaClient 추가 
- GateWay 설정 변경  
- AD Service /api/ads => /ads 로 변경 
- 구동 확인
- 타 서비스 호출을 위한 Feign Client 추가 <- Backend-Service (AdsServiceClient 클래스 참고)

## 4/3 토요일
- Currency 서비스 새로만들기
- 기존 Currency 서비스 가져오기 
- Currency 에서 Redis를 사용하도록 수정
- Redis에 환율 초기값 설정하는 방법을 몰라서 fetchCurrency 에서 강제 할당함 *****
- api-gateway에 CURRENCY 활성화 (8094 Port 사용)
- eureka client 등록
- //TO-DO
- Redis 에 초기값 넣고 fetchCurrency 정상화
- currency 서비스에서 payment 의 Money Class 를 참조하는데 참조가 안되서 Money 클래스를 복사하여 가져옴
  --> 어떻게 처리해야 하는지???
- Monorithic 에서 currency 제거

## 4/4 일요일
- Backend 서비스 복사하여 Shipping 서비스 만들기
- shipping db 신규 생성 docker-compose수정 (5434 Port사용)
- api-gateway에 SHIPPING 활성화 (8096 Port 사용)
- //TO-DO
- shipping에서 cart 의 CartItem Class 참조, 복사하여 가져감 --> 정리 필요
- shipping에서 payment의 Money Class 참조, 복사하여 가져감 --> 정리 필요
- shipping에서 payment의 Money 관련 테이블 생성?? MoneyConverter 복사하여 가져감 --> 정리 필요
- 기존 Monolithic 에서 Order시 Shipping 서비스를 참조하도록 수정 필요
- Monorithic 에서 shipping 제거

## 4/11 일요일
- Backend 서비스에서 ad, currency 제거
- import.sql 에서 ad, currency 제거
- 테스트를 위한 scripts 생성
  * 전체 서비스 시작 : ./startAll.sh
  * 전체 서비스 중지 : ./stopAll.sh
  * 각각 서비스 시작 : ex) ./start_serv.sh adservice
  * 각각 서비스 중지 : ex) ./stop_serv.sh adservice
  * 각각 서비스 상태 : ex) ./status_serv.sh adservice
- rabbitmq 설치 docer-compose 추가
  * http://localhost:15672  rabbitmq 대시보드 (guest / guest)
  * rabbitmq : 5672 port 

## 4/12 월요일

- RabbitMQ 연결관련 참고자료 
https://brunch.co.kr/@springboot/298

- Shipping Service , Backend 에 대한 메시지 큐 적용 
  - /checkouts/test 호출시 JSON 스트링 발송 
  - Shipping 에서 받아서 출력만 하도록 설정 되어 있음

## 4/13 화요일

- user 서비스 분리 (8097 Port 사용)
- docker-compose 수정 : user를 위한 DB신규 추가
- backend import.sql에서 user 부분을 제거 함
- login 조금 수정 : logout 버튼 대신 login 버튼을 넣으려고 했으나 잘 안됨
- 의도 : login 전에는 login 버튼, login 이후에는 logout 버튼으로 보이도록 한다.

- TO-DO
  * backend에서 login 모듈 제거
  * login 모듈 삭제시 오류 발생 : products 에서 login을 참고하는 것 같으나 참조하는 곳을 못찾겠음 T.T
  
## 4/18 일요일

- User Service 제거 (Backend) : 그전에 안됐던 이유는 Spring Security 설정에 포함 되어 있는 부분들이 제거가 되지 않아서 문제가 됨
- Front-End에서 로그인 상태를 나타낼수 있는 isLogin 변수를 스토어에 두어, 상태에 따라서 버튼을 바꾸도록 작업 함
- 기능 정상적으로 돌아 가는 것까지 확인함

## 4/19 월요일
### 오전
- Backend 에서 Shipping Service를 지우고, 직접 호출해서 서로 통신하도록 변경함 
- Shipping Service에 있으면서 Controller Endpoint 없는부분들은 호출 할수 있는 Endpoint 새로 생성함
- URL 정리 작업 진행
### 오후
- 배송실패 API 작성 : http://localhost:8096/shippings/cancel/1
- 배송실패시 Backend로 메시지 전송 -> 백엔드서비스에서는 주문을 취소(isCaneled필드 셋팅) 함 (각 서버에 리스터 및 템플릿 추가)
- 주문목록 화면에서 배송 실패처리된 건에 대해서는 cancel 버튼이 보여지지 않도록 함


## 4/20-21 화요일 수요일
- Spring boot 버전 변겅  => 2.3.5 RELEASE
- Cloud version 변경  => Hoxton.SR10
- Jaeger 설정을 위한 라이브러리, 설정파일 모든 프로젝트 대상으로 추가
- Jaeger All in  one 도커 기반 실행을 위한 Shell 파일 생성
- Jageger 확인  JaeerUI: http://localhost:16686/ 

## Grafana prometheus
```
 git clone https://github.com/rabbitmq/rabbitmq-prometheus.git
 ./rabbitmq-prometheus-run.sh
 ```