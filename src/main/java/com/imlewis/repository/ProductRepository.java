package com.imlewis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.imlewis.model.Category;
import com.imlewis.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long>, PagingAndSortingRepository<Product, Long>{
	
	List<Product> findAllByOrderByProductViewsDesc();
	
	List<Product> findAllByProductCategory(Category category);
	
	@Query(value = "select * from PRODUCT where REFERRAL_GIFT = TRUE", nativeQuery = true)
	List<Product> getAllByProductGifts();
}
