package com.sparta.springcore.cafe.domain;

import com.sparta.springcore.model.Timestamped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CafeImage extends Timestamped {

    @Id
    @GeneratedValue
    @Column(name = "cafe_image_number")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_number")
    private Cafe cafe;

    private String cafeImageUrl;
}
