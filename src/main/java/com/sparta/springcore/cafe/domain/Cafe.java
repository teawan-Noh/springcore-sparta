package com.sparta.springcore.cafe.domain;
import com.sparta.springcore.cafe.dto.CafeCreatRequestDto;
import com.sparta.springcore.model.Timestamped;
import com.sparta.springcore.model.User;
import lombok.*;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
//@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 다른곳에서 생성자 못쓰도록 막아둠
public class Cafe extends Timestamped {

    @Id @GeneratedValue
    @Column(name = "cafe_number")
    private Long id;

    @Column(nullable = false)
    private String cafeName;

    private int cafeZonecode;

    private String cafeAddress;

    @Column(nullable = false)
    private String cafeAddressDetail;

    // 경도 : x : Longitude
    private Double cafeX;

    // 위도 : y : Latitude
    private Double cafeY;

    private String cafeInfo;

    private String cafeInfoDetail;

    private String cafePrecaution;

//    @Column(nullable = false)
    private int cafeWeekdayPrice;

//    @Column(nullable = false)
    private int cafeWeekendPrice;

    // 단반향 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;

    @OneToMany(mappedBy = "cafe", cascade = CascadeType.ALL)
    private List<CafeOption> cafeOptions = new ArrayList<>();

    @OneToMany(mappedBy = "cafe", cascade = CascadeType.ALL)
    private List<CafeImage> cafeImages = new ArrayList<>();
//
//    @OneToMany(mappedBy = "cafe")
//    private List<CafeReview> cafeReviews = new ArrayList<>();
//
//    @OneToMany(mappedBy = "cafe")
//    private List<CafeSchedule> cafeSchedules = new ArrayList<>();
//
//    @OneToMany(mappedBy = "cafe")
//    private List<Event> events = new ArrayList<>();

    public Cafe(CafeCreatRequestDto requestDto) {
        this.cafeName = requestDto.getCafeName();
        this.cafeZonecode = requestDto.getCafeZonecode();
        this.cafeAddress = requestDto.getCafeAddress();
        this.cafeAddressDetail = requestDto.getCafeAddressDetail();
        this.cafeX = requestDto.getCafeX();
        this.cafeY = requestDto.getCafeY();
        this.cafeInfo = requestDto.getCafeInfo();
        this.cafeInfoDetail = requestDto.getCafeInfoDetail();
        this.cafePrecaution = requestDto.getCafePrecaution();
        this.cafeWeekdayPrice = requestDto.getCafeWeekdayPrice();
        this.cafeWeekendPrice = requestDto.getCafeWeekendPrice();
    }

    public void addUser(User user){
        this.user = user;
        user.getCafes().add(this); // 양방향 처리를 잘해주자
    }
    //==연관관계 편의 메서드==//
    public void addCafeImage(CafeImage cafeImage){
        cafeImages.add(cafeImage);
        cafeImage.addCafe(this);
    }

    public void addCafeOption(CafeOption cafeOption) {
        cafeOptions.add(cafeOption);
        cafeOption.addCafe(this);
    }

    //==생성 메서드==// 여러개의 연관관계로 복잡한 생성은 별도의 생성 메서드가 있으면 좋다.
    public static Cafe registCafe(CafeCreatRequestDto requestDto, User user, List<CafeImage> cafeImages, List<CafeOption> cafeOptions){

        Cafe cafe = new Cafe();
        cafe.addUser(user);
        for (CafeImage cafeImage : cafeImages) {
            cafe.addCafeImage(cafeImage);
        }
        for (CafeOption cafeOption : cafeOptions) {
            cafe.addCafeOption(cafeOption);
        }

        cafe.cafeName = requestDto.getCafeName();
        cafe.cafeZonecode = requestDto.getCafeZonecode();
        cafe.cafeAddress = requestDto.getCafeAddress();
        cafe.cafeAddressDetail = requestDto.getCafeAddressDetail();
        cafe.cafeX = requestDto.getCafeX();
        cafe.cafeY = requestDto.getCafeY();
        cafe.cafeInfo = requestDto.getCafeInfo();
        cafe.cafeInfoDetail = requestDto.getCafeInfoDetail();
        cafe.cafePrecaution = requestDto.getCafePrecaution();
        cafe.cafeWeekdayPrice = requestDto.getCafeWeekdayPrice();
        cafe.cafeWeekendPrice = requestDto.getCafeWeekendPrice();
        return cafe;
    }
}
