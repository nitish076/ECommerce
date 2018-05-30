package com.ecommerce.ECommerce.Repository;

import com.ecommerce.ECommerce.model.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomQueryBySearch {

    List<Product> search(List<String> searchWords, Pageable pageable);

}
