package com.example.fast_sns.domain.member.service;

import com.example.fast_sns.domain.member.dto.RegisterMemberCommand;
import com.example.fast_sns.domain.member.entity.Member;
import com.example.fast_sns.domain.member.entity.MemberNicknameHistory;
import com.example.fast_sns.domain.member.repository.MemberNicknameHistoryRepository;
import com.example.fast_sns.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberWriteService {
    final private MemberRepository memberRepository;

    final private MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    public Member register(RegisterMemberCommand command) {
        Member member = Member.builder()
                .nickname(command.nickname())
                .email(command.email())
                .birthday(command.birthday())
                .build();

        var saveMember = memberRepository.save(member);
        saveMemberNicknameHistory(saveMember);
        return saveMember;
    }

    public void changeNickname(Long memberId, String nickname) {

        var member = memberRepository.findById(memberId).orElseThrow();
        member.changeNickname(nickname);
        memberRepository.save(member);

        saveMemberNicknameHistory(member);
    }

    private void saveMemberNicknameHistory(Member member) {
        var history = MemberNicknameHistory.builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .build();

        memberNicknameHistoryRepository.save(history);
    }
}
