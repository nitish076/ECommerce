package com.ecommerce.ECommerce.services;

import com.ecommerce.ECommerce.Dto.ProductReceiveDto;
import com.ecommerce.ECommerce.Dto.ProductSendDto;
import com.ecommerce.ECommerce.Repository.ProductRepositoryBySearchInterface;
import com.ecommerce.ECommerce.model.Product;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceSearchImpl implements SearchServiceInterface {

    @Value("${weight.list}")
    private String[] weightList;

    @Autowired
    private ProductRepositoryBySearchInterface productRepositoryBySearch;

    @Override
    public List<ProductSendDto> getProduct(String searchTerm, Pageable pageable) {

        ArrayList<String> words = searchTextPruning(searchTerm);

        List<Product> tempProductList = productRepositoryBySearch.search(words,pageable);

        List<ProductSendDto> productSendDtos = new ArrayList<>();
        for (Product product : tempProductList) {
            productSendDtos.add(new ProductSendDto(product));
        }

        return productSendDtos;
    }

    @Override
    public Product addProduct(ProductReceiveDto productReceiveDto) {
        String id = productReceiveDto.getProductName() + productReceiveDto.getMerchantName();
        Optional<Product> productOpt = productRepositoryBySearch.findById(id);
        Product product;
        if (productOpt.isPresent()) {
            product = productOpt.get();
            product.setStock(product.getStock()+productReceiveDto.getStock());//increase stock
            product.setSearchRating(calculateSearchRating(product));//call calculate rating and upadte it in object
            return productRepositoryBySearch.save(product);//then update it to solr
        }
        else {
            Product productTemp=new Product(productReceiveDto); //create new entry
            productRepositoryBySearch.save(productTemp);
            productTemp.setSearchRating(calculateSearchRating(productTemp));//set product rating
            return productRepositoryBySearch.save(productTemp);//then update to solr
        }
    }

    @Override
    public  Product productOrdered(String productName,String pidOrdered,String merchantName,int quantity)
    {
       Product product = productRepositoryBySearch.findById(productName+merchantName).get();
       product.setStock(product.getStock()-quantity);

       product.setSearchRating(calculateSearchRating(product));
       return productRepositoryBySearch.save(product);
    }

    @Override
    public Product productRated(String productName,String merchantName, Double userRating)
    {
        Product product = productRepositoryBySearch.findById(productName+merchantName).get();
        product.setProductRating(((product.getProductRating()*(product.getNumberProductSold()-1))+userRating)/product.getNumberProductSold());
        productRepositoryBySearch.save(product);

        List<Product> products=productRepositoryBySearch.findByMerchantName(merchantName);
        Double tempProductRatingSum=0.0;
        int totalNoOfProductSold=0;
        for(Product tempProduct:products)
        {
            totalNoOfProductSold=totalNoOfProductSold+tempProduct.getNumberProductSold();
            tempProductRatingSum=tempProduct.getNumberProductSold()*tempProduct.getProductRating()+tempProductRatingSum;
        }
        Product tempProduct = productRepositoryBySearch.findById(productName+merchantName).get();
        tempProduct.setMerchantRating(tempProductRatingSum/totalNoOfProductSold);

        tempProduct.setSearchRating(calculateSearchRating(tempProduct));

        return productRepositoryBySearch.save(tempProduct);
    }

    private Double calculateSearchRating(Product tempProduct) {
        List<Product> products=productRepositoryBySearch.findByMerchantName(tempProduct.getMerchantName());//get count of no of product sold by merchant by searching with merchant name
        int countProductsWrtMerchantName=products.size();
        Double searchRating=(Double.parseDouble(weightList[0]))*(countProductsWrtMerchantName*1.0)+(Integer.parseInt(weightList[1]))*(tempProduct.getNumberProductSold()*1.0);//apply algo to calculate ;you have got required data
        searchRating=searchRating+(Integer.parseInt(weightList[2]))*tempProduct.getStock()+(Integer.parseInt(weightList[3]))*tempProduct.getMerchantRating();
        searchRating=searchRating+(Integer.parseInt(weightList[4]))*tempProduct.getProductPrice()+(Integer.parseInt(weightList[5]))*tempProduct.getProductRating();
        searchRating=searchRating/6;
        return searchRating;//return rating of product
    }

    private ArrayList<String> searchTextPruning(String searchWord)
    {
        int tempIndex=0;
        ArrayList<String> tempStrings = new ArrayList<String>();
        for(int i=0;i<searchWord.length();i++)
        {
            if(!((65<=(int)searchWord.charAt(i) && (int)searchWord.charAt(i)<=90)|| (97<=(int)searchWord.charAt(i) &&(int)searchWord.charAt(i)<=122)))
            {
                if(searchWord.substring(tempIndex,i).length()>2)
                    tempStrings.add(searchWord.substring(tempIndex,i));
                tempIndex=i+1;
            }
        }
        tempStrings.add(searchWord.substring(tempIndex,searchWord.length()));
        return tempStrings;
    }


}

