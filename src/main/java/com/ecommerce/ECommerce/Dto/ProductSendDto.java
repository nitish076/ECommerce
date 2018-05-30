package com.ecommerce.ECommerce.Dto;
import com.ecommerce.ECommerce.model.Product;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProductSendDto implements Comparable<ProductSendDto>{

    private String productName;
    private String merchantName;
    private String imgSrc;
    private Double productPrice;
    private Double merchantRating;
    private int stock;
    private String categoryName;
    private Double productRating;
    private Double searchRating;
    private String productDesc;
    private String pid;
    private String mid;

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

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public ProductSendDto(Product product) {
        this.setProductName(product.getProductName());
        this.setCategoryName(product.getCategoryName());
        this.setImgSrc(product.getImgSrc());
        this.setMerchantName(product.getMerchantName());
        this.setMerchantRating(product.getMerchantRating());
        this.setProductPrice(product.getProductPrice());
        this.setStock(product.getStock());
        this.setProductRating(product.getProductRating());
        this.setSearchRating(product.getSearchRating());
        this.setProductDesc(product.getProductDescription());
        this.setPid(product.getPid());
        this.setMid(product.getMid());
    }

    public ProductSendDto() {
    }


    public Double getProductRating() {
        return productRating;
    }

    public void setProductRating(Double productRating) {
        this.productRating = productRating;
    }

    public Double getSearchRating() {
        return searchRating;
    }

    public void setSearchRating(Double searchRating) {
        this.searchRating = searchRating;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    @Override public int compareTo(ProductSendDto compareproduct) {
        Double compareSearchRating=((ProductSendDto)compareproduct).getSearchRating();
        /* For Ascending order
        return this.studentage-compareage;*/

        /* For Descending order do like this */
        Double temp= compareSearchRating-this.searchRating;
        if(temp==0)
            return 0;
        else if(temp>1)
            return 1;
        else
            return -1;
    }

}
