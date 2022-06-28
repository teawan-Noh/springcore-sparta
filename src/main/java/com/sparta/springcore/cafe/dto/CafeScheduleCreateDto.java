package com.sparta.springcore.cafe.dto;

import com.sparta.springcore.cafe.domain.CafeScheduleType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
public class CafeScheduleCreateDto {

    private LocalDate cafeScheduleStartDate;
    private LocalDate cafeScheduleEndDate;
    private String cafeScheduleInfo;
    private CafeScheduleType cafeScheduleType;
    private int cafeSchedulePrice;
}
