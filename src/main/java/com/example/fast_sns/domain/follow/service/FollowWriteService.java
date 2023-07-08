package com.example.fast_sns.domain.follow.service;

import com.example.fast_sns.domain.follow.entity.Follow;
import com.example.fast_sns.domain.follow.repository.FollowRepository;
import com.example.fast_sns.domain.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FollowWriteService {
    private final FollowRepository followRepository;

    public void create(MemberDto fromMember, MemberDto toMember) {
        Assert.isTrue(!fromMember.id().equals(toMember.id()), "From, To 회원이 동일합니다.");

        var follow = Follow.builder()
                .fromMemberId(fromMember.id())
                .toMemberId(toMember.id())
                .build();

        followRepository.save(follow);
    }

}
