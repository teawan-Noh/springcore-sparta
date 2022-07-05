package com.sparta.springcore.cafe.repository;

import com.sparta.springcore.cafe.domain.CafeOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CafeOptionRepository extends JpaRepository<CafeOption, Long> {
}
