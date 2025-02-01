package com.experiment.daeseda_renewal.constant;

public enum SignupStatus {
    SUCCESS("회원가입 성공!"),
    EMAIL_ALREADY_EXISTS("이미 사용 중인 이메일입니다."),
    FAIL("회원가입에 실패하였습니다.");

    private final String message;

    SignupStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
