package com.example.fast_sns.domain.post.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record DailyPostCountRequest(
        Long memberId,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate firstDate,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate lastDate
) {
}
