package com.sparta.springcore.cafe.domain;

import com.sparta.springcore.model.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CafeOption {

    @Id
    @GeneratedValue
    @Column(name = "cafe_option_number")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_number")
    private Cafe cafe;

    @Column(nullable = false)
//    @Enumerated(EnumType.STRING)
    private CafeOptionType cafeOptionType;

    private String cafeOptionIcon;

    public CafeOption(CafeOptionType option){
        this.cafeOptionType = option;
//        this.cafe = cafe;
    }

    public void addCafe(Cafe cafe) {
        this.cafe = cafe;
    }
}
