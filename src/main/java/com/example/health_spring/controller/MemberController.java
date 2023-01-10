package com.example.health_spring.controller;


import com.example.health_spring.entity.Member;
import com.example.health_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam("id") String id, @RequestParam("password") String password) {
        ResponseEntity<String> res = null;
        try {
            String loginId = memberService.login(id,password);
            res = new ResponseEntity<String>(loginId, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            res = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        return res;
    }

    @PostMapping("/join")
    public ResponseEntity<Boolean> join(@ModelAttribute Member member) {
        System.out.println(member);
        ResponseEntity<Boolean> res = null;
        try {
            memberService.join(member);
            res = new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            res = new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
        }
        return res;
    }
    @PostMapping("/doubleid")
    public ResponseEntity<Boolean> doubledId(@RequestParam("id") String id) {
        ResponseEntity<Boolean> res = null;
        try {
            Boolean isdouble = memberService.checkDoubleId(id);
            res = new ResponseEntity<Boolean>(isdouble, HttpStatus.OK);
        } catch (Exception e) {
            res = new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
        }
        return res;
    }
}
