package com.example.fast_sns.controller;

import com.example.fast_sns.domain.member.dto.MemberDto;
import com.example.fast_sns.domain.member.dto.MemberNicknameHistoryDto;
import com.example.fast_sns.domain.member.dto.RegisterMemberCommand;
import com.example.fast_sns.domain.member.entity.Member;
import com.example.fast_sns.domain.member.repository.MemberRepository;
import com.example.fast_sns.domain.member.service.MemberReadService;
import com.example.fast_sns.domain.member.service.MemberWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberWriteService memberWriteService;

    private final MemberReadService memberReadService;

    @PostMapping("/members")
    public MemberDto register(@RequestBody RegisterMemberCommand command) {
        var member = memberWriteService.register(command);
        return memberReadService.toDto(member);
    }

    @GetMapping("/members/{id}")
    public MemberDto getMember(@PathVariable Long id) {
        return memberReadService.getMember(id);
    }

    @PutMapping("/{id}/name")
    public MemberDto changeNickname(@PathVariable Long id, @RequestBody String nickname) {
        memberWriteService.changeNickname(id, nickname);
        return memberReadService.getMember(id);
    }

    @GetMapping("/{memberId}/nickname-histories")
    public List<MemberNicknameHistoryDto> getNicknameHistories(@PathVariable Long memberId) {
        return memberReadService.getNicknameHistories(memberId);
    }
}
