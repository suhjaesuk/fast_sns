package com.example.fast_sns.domain.post.dto;

public record PostCommand(
        Long memberId,
        String contents
) {
}
