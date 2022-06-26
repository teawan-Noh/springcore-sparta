package sparta.projectprac.cafe.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import sparta.projectprac.common.Timestamped;
import sparta.projectprac.user.model.User;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 다른곳에서 생성자 못쓰도록 막아둠
public class Cafe extends Timestamped{

    @Id @GeneratedValue
    @Column(name = "cafe_number")
    private Long id;

    // 단반향 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_number")
    private User user;

    @Column(nullable = false)
    private String cafeName;

    // zipcode == zonecode 필요 없어 보여서 지워도 될듯
    // 지번주소, 도로명주소 
    private String cafeJibunAddress;

    private String cafeRoadAddress;

    @Column(nullable = false)
    private String cafeAddressDetail;

    // 위도 : y : Latitude
    @Column(nullable = false)
    private String cafeX;

    // 경도 : x : Longitude
    @Column(nullable = false)
    private String cafeY;

    private String cafeInfo;

    private String cafeInfoDetail;

    private String cafePrecaution;

    @Column(nullable = false)
    private int cafeWeekdayPrice;

    @Column(nullable = false)
    private int cafeWeekendPrice;

}
