package com.sparta.springcore.cafe.service;

import com.sparta.springcore.cafe.domain.Cafe;
import com.sparta.springcore.cafe.domain.CafeImage;
import com.sparta.springcore.cafe.dto.CafeCreatRequestDto;
import com.sparta.springcore.cafe.repository.CafeImageRepository;
import com.sparta.springcore.cafe.repository.CafeRepository;
import com.sparta.springcore.model.User;
import com.sparta.springcore.repository.UserRepository;
import com.sparta.springcore.s3.S3Service;
import com.sparta.springcore.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

//@Transactional(readOnly = true)
//@Transactional
@Service
@RequiredArgsConstructor
public class CafeService {

    private final CafeRepository cafeRepository;
    private final UserRepository userRepoistory;
    private final CafeImageRepository cafeImageRepository;
    private final S3Service s3Service;

    @Transactional
    public void createCafe(CafeCreatRequestDto requestDto, UserDetailsImpl userDetails) {
        // 스프링 시큐리티의 유저 정보
        User user = userDetails.getUser();
        // 시큐리티의 유저 정보를 토대로 해당 USER가 디비에 있는지 확인 할 경우
//        Long userId = userDetails.getUser().getId();
//        User user2 = userRepoistory.getById(userId);

        List<MultipartFile> files = requestDto.getFiles();
        // s3저장 후 url 반환받음
        List<String> cafeImageUrlList = s3Service.upload(files, "cafeImage");

        // 1번 case : cafe
        Cafe cafe = new Cafe(requestDto, user);
        cafeRepository.save(cafe);

        List<CafeImage> cafeImageEntityList = new ArrayList<>();
        MultipartFile file;
        String cafeImageUrl;
        for (int i = 0; i < files.size(); i++){
            file = files.get(i);
            cafeImageUrl = cafeImageUrlList.get(i);
            CafeImage cafeImage = new CafeImage(file.getOriginalFilename(), cafeImageUrl, cafe);
            cafeImageEntityList.add(cafeImage);
        }
        cafeImageRepository.saveAll(cafeImageEntityList);

        // 2번 case : service에서 빌더로 생성 -> 단점 : 데이터의 변경처리를 한다.
//        Cafe cafe = Cafe.builder()
//                .cafeName(requestDto.getCafeName())
//                .cafeAddress("adc222")
//                .build();

        // 3번 case -> dto에서 생성 -> something 위반사항 있어서 실무에서 안쓴다고함.
    }
}
