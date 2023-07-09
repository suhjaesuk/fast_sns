package com.example.fast_sns.domain.post.service;

import com.example.fast_sns.domain.post.entity.TimeLine;
import com.example.fast_sns.domain.post.repository.TimeLineRepository;
import com.example.fast_sns.util.CursorRequest;
import com.example.fast_sns.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TimeLineReadService {
    final private TimeLineRepository timeLineRepository;

    public PageCursor<TimeLine> getTimeLines(Long memberId, CursorRequest cursorRequest) {
        var timeLines = findAllBy(memberId, cursorRequest);
        var nextKey = timeLines.stream()
                .mapToLong(TimeLine::getId)
                .min().orElse(CursorRequest.NONE_KEY);
        return new PageCursor<>(cursorRequest.next(nextKey), timeLines);
    }

    private List<TimeLine> findAllBy(Long memberId, CursorRequest cursorRequest) {
        if (cursorRequest.hasKey()) {
            return timeLineRepository.findAllByLessThanIdAndMemberIdAndOrderByIdDesc(
                    cursorRequest.key(),
                    memberId,
                    cursorRequest.size()
            );
        }

        return timeLineRepository.findAllByMemberIdAndOrderByIdDesc(memberId, cursorRequest.size());
    }
}