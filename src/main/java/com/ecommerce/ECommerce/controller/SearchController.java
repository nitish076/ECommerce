package com.ecommerce.ECommerce.controller;

import com.ecommerce.ECommerce.Dto.ProductReceiveDto;
import com.ecommerce.ECommerce.Dto.ProductSendDto;
import com.ecommerce.ECommerce.ExceptionHandler.CustomProductAddException;
import com.ecommerce.ECommerce.ExceptionHandler.CustomProductOrderedException;
import com.ecommerce.ECommerce.ExceptionHandler.CustomProductRatedException;
import com.ecommerce.ECommerce.model.Product;
import com.ecommerce.ECommerce.services.SearchServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.SolrPageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;


@RestController
public class SearchController{

    @Autowired
    private SearchServiceInterface searchService;

    @RequestMapping("/search")
    public ResponseEntity<List<ProductSendDto>> SearchProducts(@RequestParam String searchTerm, @RequestParam int page, @RequestParam int pageSize) throws Exception {

        if(searchTerm==null)
            throw new Exception("product not found");
        Pageable pageable =new SolrPageRequest(page, pageSize);
        List<ProductSendDto> tempProductSendDtos= searchService.getProduct(searchTerm,pageable);
        if(tempProductSendDtos.size()==0)
            throw new Exception("product not found");
       return new ResponseEntity<>(tempProductSendDtos,HttpStatus.OK);
    }

    //@RequestBody ProductReceiveDto productReceiveDto
    @RequestMapping("/addnewproduct")
    public ResponseEntity<Boolean> addNewProduct(@RequestParam String pid,@RequestParam String mid,@RequestParam String productName,
     @RequestParam Double productPrice,@RequestParam int stock,@RequestParam String productDescription, @RequestParam String merchantName,
                                                 @RequestParam String productCategory,@RequestParam String imgSrc,
                                                 @RequestParam Double productRating,@RequestParam Boolean isIndexed) throws CustomProductAddException,Exception {

        pid=URLDecoder.decode(pid, StandardCharsets.UTF_8.toString());
        mid=URLDecoder.decode(mid, StandardCharsets.UTF_8.toString());
        productName=URLDecoder.decode(productName, StandardCharsets.UTF_8.toString());
        productDescription=URLDecoder.decode(productDescription, StandardCharsets.UTF_8.toString());
        merchantName=URLDecoder.decode(merchantName, StandardCharsets.UTF_8.toString());
        productCategory=URLDecoder.decode(productCategory, StandardCharsets.UTF_8.toString());

        ProductReceiveDto productReceiveDto=new ProductReceiveDto(pid,mid,productName,productPrice,stock,productDescription,
                merchantName,productCategory,imgSrc,5.0,productRating,isIndexed);
        Product product=searchService.addProduct(productReceiveDto);
        if(product.equals(null))
            throw new CustomProductAddException("document not saved");
        return new ResponseEntity<>(true,HttpStatus.OK);
    }

    @RequestMapping("/productordered")
    public ResponseEntity<Boolean> productOrdered(@RequestParam String productName,@RequestParam String pidOrdered,@RequestParam String merchantName,@RequestParam int quantity) throws CustomProductOrderedException , Exception {
        Product product=searchService.productOrdered(productName,pidOrdered,merchantName,quantity);
        if(product.equals(null))
            throw new CustomProductOrderedException("document not saved");
        return new ResponseEntity<>(true,HttpStatus.OK);
    }


    @RequestMapping("/userrating")
    public ResponseEntity<Boolean> productRatedByUser(@RequestParam String productName,@RequestParam String merchantName,@RequestParam Double userRating) throws CustomProductRatedException , Exception{
        Product product=searchService.productRated(productName,merchantName,userRating);
        if(product.equals(null))
            throw new CustomProductRatedException("product rating error");
        return new ResponseEntity<>(true,HttpStatus.OK);
    }

}