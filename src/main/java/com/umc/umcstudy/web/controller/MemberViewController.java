package com.umc.umcstudy.web.controller;

import com.umc.umcstudy.web.dto.member.MemberRequestDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberViewController {

  // login.html
  @GetMapping("/login")
  public String loginPage() {
    return "login";
  }

  // signup.html + 회원가입 폼에 필요한 빈 DTO 객체를 모델에 추가
  @GetMapping("/signup")
  public String signupPage(Model model) {
    model.addAttribute("memberJoinDto", new MemberRequestDTO.JoinDto());
    return "signup";
  }

  // home.html
  @GetMapping("/home")
  public String home() {
    return "home";
  }

  // admin.html
  @GetMapping("/admin")
  public String admin() {
    return "admin";
  }
}
