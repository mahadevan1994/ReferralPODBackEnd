package com.imlewis.repository;

import org.springframework.data.repository.CrudRepository;

import com.imlewis.model.ProductTag;

public interface ProductTagRepository extends CrudRepository<ProductTag, Long>{
	
//	@Query(value = "SELECT DISTINCT tag_contents from product_tag" , nativeQuery = true)
//	List<ProductTag> findAllTags();
}
