## 대세다 리뉴얼

### 현대인을 위한 대신 세탁 서비스 리뉴얼
---

## 버전 정보 

 - Java 17<br/>
 - Spring Boot 3.4.1<br/>
 - PostgreSQL 14<br/>
 - Redis<br/>

```
## 프로젝트 디렉토리 구조

daeseda-renewal/
├── config/               # 환경 설정 관련 클래스
├── constant/             # 상수 정의
├── domain/               # 비즈니스 도메인 계층
│   ├── user/             # 사용자 관련 로직
│   ├── product/          # 상품 관련 로직
│   └── ...               # 기타 도메인
├── global/               # 예외, 공통 응답 포맷, 유틸리티 등
│   ├── exception/        # 전역 예외 처리
│   └── util/             # 공통 유틸
└── DaesedaRenewalApplication.java  # 메인 실행 클래스
```

## 프로젝트 목표
- SSR(Server-Side Rendering) 방식으로 아키텍처 전환
- DB 설계를 도메인 중심으로 리팩토링하고, 명세 기반 ERD 문서화
- PostgreSQL 기반의 마스터-슬레이브 복제 구성으로 데이터 안정성과 가용성 확보
- 서비스 모듈 구조 재설계 및 레거시 코드 정리
- 핵심 도메인 기능 추가 및 사용자 경험(UX) 개선
- Jenkins 기반 CI/CD 파이프라인 구축을 통한 자동 배포 체계 도입
- 서비스별 단위/통합 테스트 작성으로 품질 관리 강화
- k6 기반 부하 테스트 도입 및 병목 구간 파악 후 성능 최적화
- AOP 기반 공통 로깅 및 예외 처리 적용으로 유지보수성 향상

## 코드 규칙
| 항목               | 규칙                                                                       |
| ---------------- | ------------------------------------------------------------------------ |
| **DTO** | setter 금지, builder 생성, 불변성 유지 (향후 record로 변경 고려), 요청/응답 분리                 |
| **Service**      | interface 기반, 구현은 Impl로 명확히 분리                                           |
| **Transaction**    | 구현체의 메서드에 `@Transactional` 명시 (`readOnly=true` 기본)                       |
| **Controller**   | RestController와 일반 Controller 분리, DB 접근 시 전부 RestController                 |
| **Exception 처리** | `@ControllerAdvice` 및 @RestControllerAdvice로 공통 처리, 필요 시 redirect + flash 처리  |
| **Entity** | 데이터 변경이 필요한 경우 entity 내부 메서드로 변경 처리 setter 사용 x, 생성은 Builder 패턴       |
| **패키지 구조**   | 도메인 기반 계층 분리 (`domain.xxx.controller`, `dto`, `entity`, ...)             |

## 네이밍 규칙
| ** CRUD 네이밍 **  | 새 리소스 생성 - Create, 단건 조회 get, 목록 조회 list, 조건 검색 search - Read, 전체 갱신 update, 부분 갱신 patch - Update, 리소스 삭제 - Delete |

