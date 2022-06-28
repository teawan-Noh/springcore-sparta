package com.sparta.springcore.cafe.service;

import com.sparta.springcore.cafe.domain.Cafe;
import com.sparta.springcore.cafe.dto.CafeCreatRequestDto;
import com.sparta.springcore.cafe.repository.CafeRepository;
import com.sparta.springcore.model.User;
import com.sparta.springcore.repository.UserRepository;
import com.sparta.springcore.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@Transactional(readOnly = true)
//@Transactional
@Service
@RequiredArgsConstructor
public class CafeService {

    private final CafeRepository cafeRepository;
    private final UserRepository userRepoistory;

    @Transactional
    public void createCafe(CafeCreatRequestDto requestDto, UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        User user = userRepoistory.getById(userId);

        // 1번 case : cafe
        Cafe cafe = new Cafe(requestDto, user);

        // 2번 case : service에서 빌더로 생성 -> 단점 : 데이터의 변경처리를 한다.
//        Cafe cafe = Cafe.builder()
//                .cafeName(requestDto.getCafeName())
//                .cafeJibunAddress("adc222")
//                .build();

        // 3번 case -> dto에서 생성 -> something 위반사항 있어서 실무에서 안쓴다고함.
        cafeRepository.save(cafe);
    }
}
