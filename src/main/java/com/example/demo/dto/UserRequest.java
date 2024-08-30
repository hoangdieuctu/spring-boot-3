package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record UserRequest(
        @NotNull @Length(min = 1, max = 64) String username,
        @NotNull @Length(min = 1, max = 256) String fullName) {
}
