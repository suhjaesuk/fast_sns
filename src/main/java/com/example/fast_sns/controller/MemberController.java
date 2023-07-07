package com.example.fast_sns.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberWriteService memberWriteService;

    private final MemberReadService memberReadService;

    @PostMapping("/members")
    public Member register(@RequestBody RegisterMemberCommand command) {
        return memberWriteService.create(command);
    }

    @GetMapping("/members/{id}")
    public Member getMember(@PathVariable Long id) {
        return memberReadService.getMember(id);
    }
}
