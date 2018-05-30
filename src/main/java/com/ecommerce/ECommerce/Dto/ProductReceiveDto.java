package com.ecommerce.ECommerce.Dto;

import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProductReceiveDto {

    private String pid;
    private String mid;
    private String productName;
    private Double productPrice;
    private int stock;
    private String productDescription;
    private String merchantName;
    private String productCategory;
    private String imgSrc;
    private Double merchantRating;
    private Double productRating;
    private boolean isIndexed;


    public ProductReceiveDto() {
    }

    public ProductReceiveDto( String pid, String mid, String productName, Double productPrice, int stock, String productDescription,
                              String merchantName, String productCategory, String imgSrc, Double merchantRating,
                             Double productRating, Boolean isIndexed) {
        this.pid=pid;
        this.mid=mid;
        this.productName=productName;
        this.productPrice=productPrice;
        this.stock=stock;
        this.productDescription=productDescription;
        this.merchantName=merchantName;
        this.productCategory=productCategory;
        this.imgSrc=imgSrc;
        this.merchantRating=merchantRating;
        this.productRating=productRating;
        this.isIndexed=isIndexed;

    }

    public Double getProductRating() {
        return productRating;
    }

    public void setProductRating(Double productRating) {
        this.productRating = productRating;
    }

    public boolean isIndexed() {
        return isIndexed;
    }

    public void setIndexed(boolean indexed) {
        isIndexed = indexed;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getPid() {

        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public Double getMerchantRating() {
        return merchantRating;
    }

    public void setMerchantRating(Double merchantRating) {
        this.merchantRating = merchantRating;
    }

}
