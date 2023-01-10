package com.example.health_spring.service;


import com.example.health_spring.entity.Member;
import com.example.health_spring.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private  final MemberRepository memberRepository;

    public  String login(String id, String password) throws Exception {
        Optional<Member> omember = memberRepository.findById(id);
        if(omember.isPresent()) {
            Member member = omember.get();
            if(member.getPassword().equals(password)){
                return member.getId();
            }else {
                throw  new Exception("비밀번호 오류");
            }
            }
        throw  new Exception("아이디 오류");
        }
        public  void join(Member member) throws  Exception{
        memberRepository.save(member);
        }

        public  Boolean checkDoubleId(String id) throws  Exception{
        Optional<Member> omember = memberRepository.findById(id);
        if(omember.isPresent()) return  true;
        return  false;
        }
    }

