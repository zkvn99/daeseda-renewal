# daeseda-renewal
daeseda-renewal

버전 정보
Java 17
Spring 3.4.1
PostgreSQL 14
Redis

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

목표
1. 기존 CSR 방식에서 SSR로 변경
2. 데이터베이스 스키마 변경 및 ERD 작성
3. DB 이중화 (Replication)
4. 기존 코드 리팩터링 (구조 변경)
5. 새로운 기능 추가 및 고도화
6. CI/CD 추가 (Jenkins)
7. 모든 서비스 테스트 코드 추가
8. 부하 테스트 및 성능 측정
9. AOP 적용
