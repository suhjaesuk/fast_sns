package com.example.fast_sns.domain.post.service;

import com.example.fast_sns.domain.post.dto.DailyPostCount;
import com.example.fast_sns.domain.post.dto.DailyPostCountRequest;
import com.example.fast_sns.domain.post.entity.Post;
import com.example.fast_sns.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostReadService {
    private final PostRepository postRepository;

    public List<DailyPostCount> getDailyPostCounts(DailyPostCountRequest request) {
        return postRepository.groupByCreatedDate(request);
    }

    public Page<Post> getPosts(Long memberId, Pageable pageRequest) {
        return postRepository.findAllByMemberId(memberId, pageRequest);
    }
}
