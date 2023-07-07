package com.example.fast_sns.domain.member.service;

import com.example.fast_sns.domain.member.dto.RegisterMemberCommand;
import com.example.fast_sns.domain.member.entity.Member;
import com.example.fast_sns.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberWriteService {
    final private MemberRepository memberRepository;

    public Member register(RegisterMemberCommand command) {
        Member member = Member.builder()
                .nickname(command.nickname())
                .email(command.email())
                .birthday(command.birthday())
                .build();

        return memberRepository.save(member);
    }

    public void changeNickname(Long memberId, String nickname) {

        var member = memberRepository.findById(memberId).orElseThrow();
        member.changeNickname(nickname);
        memberRepository.save(member);
        // TODO: 변경 내역 히스토리
    }
}
