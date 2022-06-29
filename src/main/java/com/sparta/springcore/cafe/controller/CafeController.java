package com.sparta.springcore.cafe.controller;

import com.sparta.springcore.cafe.dto.CafeCreatRequestDto;
import com.sparta.springcore.cafe.service.CafeService;
import com.sparta.springcore.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CafeController {

    private final CafeService cafeService;

    @GetMapping("/cafes/new")
    public String cafeCreateForm(Model model){

        model.addAttribute("cafeCreatRequestDto", new CafeCreatRequestDto());

        return "cafe/createCafeForm";
    }
    // @RequestBody @Valid CafeRequestDto requestDto, BindingResult result
    // @RequestBody 넣은 경우 Content type 'application/x-www-form-urlencoded;charset=UTF-8' not supported
    // ajax 호춣이 아니라서 ContentType 지정을 json으로 못함. -> 데이터 타입 에러
    @PostMapping("/cafes")
    public String cafeCreate(@Valid @ModelAttribute CafeCreatRequestDto requestDto, BindingResult result,
                             @AuthenticationPrincipal UserDetailsImpl userDetails){

        if(result.hasErrors()){
            return "cafe/createCafeForm";
        }
        // service로직에서 userDetails을 활용 할 경우를 고려하여 id가 아닌 userDetails 자체를 넘기.
        cafeService.createCafe(requestDto, userDetails);



        return "redirect:/";
    }
}
