package com.example.fast_sns.domain.post;

import com.example.fast_sns.domain.post.entity.Post;
import com.example.fast_sns.domain.post.repository.PostRepository;
import com.example.fast_sns.util.PostFixtureFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.time.LocalDate;
import java.util.stream.IntStream;

@SpringBootTest
public class PostBulkInsertTest {
    @Autowired
    private PostRepository postRepository;

    @Test
    public void bulkInsert() {
        var easyRandom = PostFixtureFactory.get(
                3L,
                LocalDate.of(2022, 1, 1),
                LocalDate.of(2022, 2, 1)
        );

        var objectStopWatch = new StopWatch();
        objectStopWatch.start();

        var posts = IntStream.range(0, 10000 * 100)
                .parallel()
                .mapToObj(i -> easyRandom.nextObject(Post.class))
                .toList();

        objectStopWatch.stop();
        System.out.println("객체 생성 시간 " + objectStopWatch.getTotalTimeSeconds());

        var queryStopWatch = new StopWatch();
        queryStopWatch.start();

        postRepository.bulkInsert(posts);

        queryStopWatch.stop();
        System.out.println("쿼리 시간 " + queryStopWatch.getTotalTimeSeconds());
    }
}
