package com.example.Board.controller;

import com.example.Board.alert.Message;
import com.example.Board.dto.User;
import com.example.Board.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@RequestMapping("/user")
@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ModelAndView signup(@Valid @ModelAttribute SignupForm signupForm, BindingResult bindingResult, ModelAndView mv){


        //if(user == null){
            //mv.addObject("data", new Message("이미 존재하는 아이디입니다.", "javascript:history.back()")); 
            //mv.setViewName("alert-page"); // 설정한 Data를 가지고 alertPage view로 이동
            //return mv;
        //}
        //else{
            //mv.addObject("data", new Message("회원가입이 완료되었습니다.", "/user/login"));
           // mv.setViewName("alert-page"); // 설정한 Data를 가지고 alertPage view로 이동
           // return mv;
        //}
    //}

 
