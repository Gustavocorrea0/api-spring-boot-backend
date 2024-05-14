package com.example.basecars.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CarRecordDto(@NotBlank @NotNull String carBrand, @NotBlank @NotNull String carName,
                           @NotBlank @NotNull String carColor, @NotNull @Max(value = 2025) @Min(value = 1900) int carYear,
                           @NotNull BigDecimal carValue) {
}
