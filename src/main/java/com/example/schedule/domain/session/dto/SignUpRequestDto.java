package com.example.schedule.domain.session.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpRequestDto {
    @NotBlank
    String email;
    @NotNull
    String password;
    @NotNull
    String name;
}
