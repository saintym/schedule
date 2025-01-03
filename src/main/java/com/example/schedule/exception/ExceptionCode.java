package com.example.schedule.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {
    INVALID_PASSWORD(404, "USER_001", "비밀번호의 형식이 올바르지 않습니다."),
    INVALID_EMAIL(404, "USER_002", "이메일의 형식이 올바르지 않거나, 중복된 이메일입니다."),

    USER_NOT_FOUND(404, "USER_003", "해당 유저를 찾을 수 없습니다."),
    USER_ID_NOT_FOUND(404, "USER_006", "해당되는 id의 사용자를 찾을 수 없습니다.");

    private final int status;
    private final String code;
    private final String message;

    ExceptionCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}