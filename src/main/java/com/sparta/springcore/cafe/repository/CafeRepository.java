package com.sparta.springcore.cafe.repository;

import com.sparta.springcore.cafe.domain.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CafeRepository extends JpaRepository<Cafe, Long>{
//    @Query("select c from Cafe c left join fetch c.cafeImages")
    List<Cafe> findAll();
}
