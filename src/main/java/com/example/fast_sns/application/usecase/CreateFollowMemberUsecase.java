package com.example.fast_sns.application.usecase;

import com.example.fast_sns.domain.follow.entity.Follow;
import com.example.fast_sns.domain.follow.service.FollowWriteService;
import com.example.fast_sns.domain.member.repository.MemberRepository;
import com.example.fast_sns.domain.member.service.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateFollowMemberUsecase {
    final private MemberReadService memberReadService;

    final private FollowWriteService followWriteService;

    public void execute(Long fromMemberId, Long toMemberId) {
        var fromMember = memberReadService.getMember(fromMemberId);
        var toMember = memberReadService.getMember(toMemberId);

        followWriteService.create(fromMember, toMember);
    }
}
