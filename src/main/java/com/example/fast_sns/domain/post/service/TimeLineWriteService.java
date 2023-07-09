package com.example.fast_sns.domain.post.service;

import com.example.fast_sns.domain.post.entity.TimeLine;
import com.example.fast_sns.domain.post.repository.TimeLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TimeLineWriteService {
    final private TimeLineRepository timeLineRepository;

    public void deliveryToTimeLine(Long postId, List<Long> toMemberIds) {
        var timeLines = toMemberIds.stream()
                .map((memberId) -> toTimeLine(postId, memberId))
                .toList();
        timeLineRepository.bulkInsert(timeLines);
    }

    private TimeLine toTimeLine(Long postId, Long memberId) {
        return TimeLine
                .builder()
                .memberId(memberId)
                .postId(postId)
                .build();
    }
}

