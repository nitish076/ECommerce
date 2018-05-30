package com.ecommerce.ECommerce.Repository;


import com.ecommerce.ECommerce.model.Product;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepositoryBySearchInterface extends CustomQueryBySearch,SolrCrudRepository<Product, String> {

    List<Product> findByMerchantName(String merchantName);

    Product save(Product product);

    boolean existsById(String Id);

}
