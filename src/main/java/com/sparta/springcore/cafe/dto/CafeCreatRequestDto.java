package com.sparta.springcore.cafe.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CafeRequestDto {

    @NotEmpty(message = "카페 이름은 필수 입니다.")
    private String cafeName;

    private int zonecode;

    private String cafeJibunAddress;

    private String cafeRoadAddress;

    @NotEmpty(message = "주소 입력은 필수 입니다.")
    private String cafeAddressDetail;

    // 경도 : x : Longitude
    private String cafeX;

    // 위도 : y : Latitude
    private String cafeY;

//    private String cafeInfo;
//
//    private String cafeInfoDetail;
//
//    private String cafePrecaution;
//
//    private int cafeWeekdayPrice;
//
//    private int cafeWeekendPrice;
}
