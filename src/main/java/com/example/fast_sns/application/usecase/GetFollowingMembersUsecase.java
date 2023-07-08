package com.example.fast_sns.application.usecase;

import com.example.fast_sns.domain.follow.entity.Follow;
import com.example.fast_sns.domain.follow.service.FollowReadService;
import com.example.fast_sns.domain.member.dto.MemberDto;
import com.example.fast_sns.domain.member.service.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetFollowingMembersUsecase {
    final private MemberReadService memberReadService;

    final private FollowReadService followReadService;

    public List<MemberDto> execute(Long memberId) {
        var followings = followReadService.getFollowings(memberId);
        var followingMemberIds = followings.stream().map(Follow::getToMemberId).toList();
        return memberReadService.getMembers(followingMemberIds);
    }
}
