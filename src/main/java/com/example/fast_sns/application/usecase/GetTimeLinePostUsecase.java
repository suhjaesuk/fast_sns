package com.example.fast_sns.application.usecase;

import com.example.fast_sns.domain.follow.entity.Follow;
import com.example.fast_sns.domain.follow.service.FollowReadService;
import com.example.fast_sns.domain.post.entity.Post;
import com.example.fast_sns.domain.post.service.PostReadService;
import com.example.fast_sns.util.CursorRequest;
import com.example.fast_sns.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetTimeLinePostUsecase {
    private final FollowReadService followReadService;

    private final PostReadService postReadService;

    public PageCursor<Post> execute(Long memberId, CursorRequest cursorRequest) {
        var follows = followReadService.getFollowings(memberId);
        var followerMemberIds = follows
                .stream()
                .map(Follow::getToMemberId)
                .toList();

        return postReadService.getPosts(followerMemberIds, cursorRequest);
    }
}
