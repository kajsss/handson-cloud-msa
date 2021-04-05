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
