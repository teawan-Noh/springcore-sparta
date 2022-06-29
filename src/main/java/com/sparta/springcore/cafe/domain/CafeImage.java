package com.sparta.springcore.cafe.domain;

import com.sparta.springcore.model.Timestamped;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CafeImage extends Timestamped {

    @Id
    @GeneratedValue
    @Column(name = "cafe_image_number")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_number")
    private Cafe cafe;

    private String cafeOriginImageName;
    private String cafeImageUrl;

    public CafeImage(String cafeOriginImageName, String cafeImageUrl, Cafe cafe){
        this.cafeOriginImageName = cafeOriginImageName;
        this.cafeImageUrl = cafeImageUrl;
        this.cafe = cafe;
    }
}
