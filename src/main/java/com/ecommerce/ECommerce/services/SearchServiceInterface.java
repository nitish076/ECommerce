package com.ecommerce.ECommerce.services;


import com.ecommerce.ECommerce.Dto.ProductReceiveDto;
import com.ecommerce.ECommerce.Dto.ProductSendDto;
import com.ecommerce.ECommerce.model.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SearchServiceInterface {

    List<ProductSendDto> getProduct(String searchTerm, Pageable pageable);

    Product addProduct(ProductReceiveDto productReceiveDto);

    Product productOrdered(String productName,String pidOrdered,String merchantName,int quantity);

    Product productRated(String productName,String merchantName, Double rating);

}
