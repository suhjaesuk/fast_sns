package com.example.fast_sns.application.usecase;

import com.example.fast_sns.domain.follow.entity.Follow;
import com.example.fast_sns.domain.follow.service.FollowReadService;
import com.example.fast_sns.domain.post.dto.PostCommand;
import com.example.fast_sns.domain.post.service.PostWriteService;
import com.example.fast_sns.domain.post.service.TimeLineWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreatePostUsecase {
    final private PostWriteService postWriteService;
    final private FollowReadService followReadService;
    final private TimeLineWriteService timeLineWriteService;

    @Transactional
    public Long execute(PostCommand command) {
        var postId = postWriteService.create(command);

        var followerMemberIds = followReadService
                .getFollowers(command.memberId()).stream()
                .map((Follow::getFromMemberId))
                .toList();

        timeLineWriteService.deliveryToTimeLine(postId, followerMemberIds);

        return postId;
    }
}
