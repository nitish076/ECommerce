package com.ecommerce.ECommerce.Repository;

import com.ecommerce.ECommerce.model.Product;
import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.GroupOptions;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.SolrResultPage;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomQueryBySearchImpl implements CustomQueryBySearch {

    @Resource
    private SolrTemplate solrTemplate;

    @Override
    public List<Product> search(List<String> searchWords, Pageable pageable) {

        Criteria conditions = createSearchConditions(searchWords);
        SimpleQuery search = new SimpleQuery(conditions);
       
           Page result=solrTemplate.queryForPage("product_core",search, Product.class);
        
        return result.getContent();
    }

    private Criteria createSearchConditions(List<String> words) {
        Criteria conditions = null;

        for (String word: words) {
            if (conditions == null) {
                conditions = new Criteria("productName").contains(word)
                        .or(new Criteria("merchantName").contains(word))
                        .or(new Criteria("categoryName").contains(word))
                        .or(new Criteria("productDescription").contains(word));
            }
            else {
                conditions = conditions.or(new Criteria("productName").contains(word))
                        .or(new Criteria("merchantName").contains(word))
                        .or(new Criteria("categoryName").contains(word))
                        .or(new Criteria("productDescription").contains(word));
            }
        }
        return conditions;
    }

    private Sort sortBySearchrating() {
            return new Sort(Sort.Direction.DESC,"searchRating");
    }

}
