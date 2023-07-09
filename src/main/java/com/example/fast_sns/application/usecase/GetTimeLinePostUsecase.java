package com.example.fast_sns.application.usecase;

import com.example.fast_sns.domain.follow.entity.Follow;
import com.example.fast_sns.domain.follow.service.FollowReadService;
import com.example.fast_sns.domain.post.entity.Post;
import com.example.fast_sns.domain.post.entity.TimeLine;
import com.example.fast_sns.domain.post.service.PostReadService;
import com.example.fast_sns.domain.post.service.TimeLineReadService;
import com.example.fast_sns.util.CursorRequest;
import com.example.fast_sns.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetTimeLinePostUsecase {
    private final FollowReadService followReadService;

    private final PostReadService postReadService;

    final private TimeLineReadService timeLineReadService;

    public PageCursor<Post> execute(Long memberId, CursorRequest cursorRequest) {
        var followings = followReadService.getFollowings(memberId);
        var followerMemberIds = followings
                .stream()
                .map(Follow::getToMemberId)
                .toList();

        return postReadService.getPosts(followerMemberIds, cursorRequest);
    }

    public PageCursor<Post> executeByTimeline(Long memberId, CursorRequest cursorRequest) {
        var pagedTimelines = timeLineReadService.getTimeLines(memberId, cursorRequest);
        var postIds = pagedTimelines.body().stream().map(TimeLine::getPostId).toList();
        var posts = postReadService.getPosts(postIds);
        return new PageCursor<>(pagedTimelines.nextCursorRequest(), posts);
    }
}
