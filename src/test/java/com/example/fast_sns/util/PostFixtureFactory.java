package com.example.fast_sns.util;

import com.example.fast_sns.domain.member.entity.Member;
import com.example.fast_sns.domain.post.entity.Post;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import java.time.LocalDate;

import static org.jeasy.random.FieldPredicates.inClass;
import static org.jeasy.random.FieldPredicates.named;
import static org.jeasy.random.FieldPredicates.ofType;

public class PostFixtureFactory {
    static public EasyRandom get(Long memberId, LocalDate firstDate, LocalDate lastDate) {
        var idPredicate = named("id").and(ofType(Long.class))
                .and(inClass(Post.class));

        var memberIdPredicate = named("memberId").and(ofType(Long.class))
                .and(inClass(Post.class));

        var param = new EasyRandomParameters()
                .excludeField(idPredicate)
                .dateRange(firstDate, lastDate)
                .randomize(memberIdPredicate, () -> memberId);

        return new EasyRandom(param);
    }
}
