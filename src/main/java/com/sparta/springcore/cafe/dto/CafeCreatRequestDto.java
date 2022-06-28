package com.sparta.springcore.cafe.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CafeCreatRequestDto {

    @NotEmpty(message = "카페 이름은 필수 입니다.")
    private String cafeName;

    private Integer cafeZonecode;

    private String cafeAddress;

    @NotEmpty(message = "주소 입력은 필수 입니다.")
    private String cafeAddressDetail;

    // 경도 : x : Longitude
    private Double cafeX;

    // 위도 : y : Latitude
    private Double cafeY;

    private String cafeInfo;

    private String cafeInfoDetail;

    private String cafePrecaution;

    private Integer cafeWeekdayPrice;

    private Integer cafeWeekendPrice;
}
