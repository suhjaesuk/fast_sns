package com.example.fast_sns.domain.member.dto;

import java.time.LocalDate;

public record RegisterMemberCommand(
    String email,
    String nickname,
    LocalDate birthday
) {
}
