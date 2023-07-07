package com.example.fast_sns.domain.member;

import com.example.fast_sns.util.MemberFixtureFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MemberTest {

    @DisplayName("회원은 닉네임을 변경할 수 있다.")
    @Test
    public void testChangeNickname() {
        var member = MemberFixtureFactory.create();
        var expectedName = "sns";

        member.changeNickname(expectedName);

        Assertions.assertEquals(expectedName, member.getNickname());
    }

    @DisplayName("회원의 닉네임은 10자를 초과할 수 없다.")
    @Test
    public void testNickNameMaxLength() {
        var member = MemberFixtureFactory.create();
        var overMaxLengthName = "snssnssnssns";

        Assertions.assertThrows(
                IllegalArgumentException.class, () ->
                member.changeNickname(overMaxLengthName)
        );
    }
}
