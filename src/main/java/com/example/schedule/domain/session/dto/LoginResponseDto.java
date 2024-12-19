package com.example.schedule.domain.session.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {
    private final Long id;
    private final boolean isSuccess;

    public LoginResponseDto(Long id, boolean isSuccess) {
        this.id = id;
        this.isSuccess = isSuccess;
    }
}