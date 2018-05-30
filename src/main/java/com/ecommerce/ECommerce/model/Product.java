package com.ecommerce.ECommerce.model;

import com.ecommerce.ECommerce.Dto.ProductReceiveDto;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.List;

@SolrDocument(solrCoreName = "product_core")
public class Product {

    @Id
    private String id;

    @Indexed(name = "productName", type = "string",boost = 2.0f)
    private String productName;

    @Indexed(name="merchantName",type="string",boost = 1.5f)
    private String merchantName;

    @Indexed(name="categoryName",type="string",boost = 1.5f)
    private String categoryName;


    @Indexed(name = "productDescription", type = "string", boost=1.0f)
    private String productDescription;// later do it for basis of search

    @Field
    private String mid;

    @Field
    private String pid;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    @Field
    private String imgSrc;

    @Field
    private Double productPrice;

    @Field
    private Double merchantRating;

    @Field
    private  Double productRating;

    @Field
    private int stock;

    @Field
    private int numberProductSold;

    @Field
    private Double searchRating;

    public Product() {
    }

    public Product(ProductReceiveDto productReceiveDto)
    {
        //all the changes which you wan to do on input productreceivedto will be done here
        this.setId(productReceiveDto.getProductName()+productReceiveDto.getMerchantName());
        this.setProductName(productReceiveDto.getProductName());
        this.setMerchantName(productReceiveDto.getMerchantName());
        this.setCategoryName(productReceiveDto.getProductCategory());
        this.setImgSrc(productReceiveDto.getImgSrc());
        this.setProductPrice(productReceiveDto.getProductPrice());
        this.setMerchantRating(productReceiveDto.getMerchantRating());
        this.setProductRating(productReceiveDto.getProductRating());//if you want to do changes in product rating do here
        this.setStock(productReceiveDto.getStock());//if you have to manage stock do changes here
        this.setProductDescription(productReceiveDto.getProductDescription());
        this.setNumberProductSold(0);
        this.setSearchRating(0.0);
        this.setMid(productReceiveDto.getMid());
        this.setPid(productReceiveDto.getPid());
    }

    public Double getSearchRating() {
        return searchRating;
    }

    public void setSearchRating(Double searchRating) {
        this.searchRating = searchRating;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Double getProductRating() {
        return productRating;
    }

    public void setProductRating(Double productRating) {
        this.productRating = productRating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Double getMerchantRating() {
        return merchantRating;
    }

    public void setMerchantRating(Double merchantRating) {
        this.merchantRating = merchantRating;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getNumberProductSold() {
        return numberProductSold;
    }

    public void setNumberProductSold(int numberProductSold) {
        this.numberProductSold = numberProductSold;
    }
}