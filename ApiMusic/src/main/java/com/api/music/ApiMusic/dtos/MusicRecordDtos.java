package com.api.music.ApiMusic.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MusicRecordDtos(@NotBlank @NotNull String nameMusic, @NotBlank @NotNull String artistMusic,
                              @NotBlank @NotNull String styleMusic, @NotNull @Max(2024) int yearMusic) {
}
