package com.experiment.daeseda_renewal.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;

/**
 * [형식] - code: 도메인 약어 - 행위 - 일련번호 (예: ADDR-CREATE-001) - message: 사용자에게 보여줄 메시지 (자연어) - httpStatus:
 * 상황에 맞는 HTTP 상태 코드 400 Bad Request             요청 자체가 잘못됨, 유효성 실패, 파라미터 누락, 요청값에 문제 있음 401
 * Unauthorized            인증 실패, 로그인 실패, 로그인 안 했거나 세션 만료 403 Forbidden	            권한 없음, 관리자 전용
 * 접근, 권한 부족, 로그인했지만 권한 없음 404 Not Found	            찾을 수 없음, 유저 ID 없음, 주소 없음, 정상 요청이지만 대상 없음 409
 * Conflict	            중복/충돌	중복 이메일, 중복 게시글 422 Unprocessable Entity	데이터는 OK, 의미가 없음	날짜 범위가 잘못됨	논리적
 * 문제 있음 500 Internal Server Error   서버 오류, DB 장애 - logLevel: 로그 기록 시 사용할 로그 수준 단순 유효성 실패는 INFO 또는
 * WARN, 시스템 장애는 ERROR로 logLevel 지정
 * <p>
 * [규칙] 1. 도메인(Domain): 기능별 책임 단위의 약어 (예: USER, ADDR, PROD, ORDER 등) 2. 행위(Action): CREATE, DELETE,
 * UPDATE, LOGIN, VALID 등 핵심 동작을 대문자로 표현 3. 일련번호(Number): 동일 도메인+행위 조합 내에서 고유하게 증가 (3자리 숫자 권장) 4.
 * 중복되거나 유사한 메시지는 피하고, 사용자와 개발자 모두에게 명확하게 전달될 수 있도록 작성
 * <p>
 * [예시] - USER-LOGIN-001: 로그인 실패 (비밀번호 불일치 등) - ADDR-DELETE-002: 주소 삭제 권한 없음 - PROD-CREATE-003: 상품
 * 등록 중 서버 오류 발생
 */


@Getter
@AllArgsConstructor
public enum ErrorCode {

  // DB 관련 에러 코드 (CRUD)
  DB_ERROR("SYS-DB-001", "데이터베이스 처리 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR,
           LogLevel.ERROR),

  // 주소 관련 에러 코드 (ADDRESS)
  DUPLICATE_ADDR("ADDR-CREATE-001", "이미 등록된 주소입니다.", HttpStatus.CONFLICT, LogLevel.INFO),
  ADDR_NOT_FOUND("ADDR_DELETE-001", "주소가 존재하지 않습니다.", HttpStatus.NOT_FOUND, LogLevel.INFO),
  ADDR_DELETE_FORBIDDEN("ADDR-DELETE-002", "주소를 삭제할 권한이 없습니다.", HttpStatus.FORBIDDEN,
                        LogLevel.WARN),

  // 유저 관련 에러 코드 (USER)
  DUPLICATE_EMAIL("USER-CREATE-001", "이미 가입된 이메일입니다.", HttpStatus.CONFLICT, LogLevel.INFO),
  LOGIN_VALID_FAILED("USER-LOGIN-001", "이메일 또는 비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED,
                     LogLevel.INFO),
  USER_NOT_FOUND("USER-FIND-001", "존재하지 않는 사용자입니다.", HttpStatus.NOT_FOUND, LogLevel.INFO);

  private final String code;
  private final String message;
  private final HttpStatus httpStatus;
  private final LogLevel logLevel;

}

