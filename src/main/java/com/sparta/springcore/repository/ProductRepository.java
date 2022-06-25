package com.sparta.springcore.repository;

import com.sparta.springcore.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // 페이징 안한 findAllByUserId
    //    List<Product> findAllByUserId(Long userId);

    // 페이징 처리한 findAllByUserId
    Page<Product> findAllByUserId(Long userId, Pageable pageable);
    Page<Product> findAllByUserIdAndFolderList_Id(Long userId, Long folderId, Pageable pageable);

}