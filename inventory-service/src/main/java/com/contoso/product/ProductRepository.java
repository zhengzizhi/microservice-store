package com.contoso.product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends CrudRepository<Product,Long> {
    Product getProductByProductId(@Param("productId") String productId);
}
