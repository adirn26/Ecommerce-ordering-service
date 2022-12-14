package com.adirn.esservice.service;

import com.adirn.basemodels.models.OrderEvent;
import com.adirn.esservice.model.CustomerES;
import com.adirn.esservice.model.OrderEventES;
import com.adirn.esservice.repository.OrderEventRepo;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QueryDslService {

    @Autowired
    private ElasticsearchRestTemplate template;

    @Autowired
    private OrderEventRepo orderEventRepo;

    public List<CustomerES> searchMultiField(String firstname, int age){
        QueryBuilder query = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("firstname", firstname))
                .must(QueryBuilders.matchQuery("age", age));
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(query).build();
        SearchHits<CustomerES> customers = template.search(nativeSearchQuery, CustomerES.class);

        //search hits to list
        List<CustomerES> customermatch = new ArrayList<CustomerES>();
        customers.forEach(customerSearchHit -> {
            customermatch.add(customerSearchHit.getContent());
        });

        return customermatch;
    }

    public List<CustomerES> multiMatch(String text){
        NativeSearchQuery query = new NativeSearchQueryBuilder().withQuery(QueryBuilders.multiMatchQuery(text)
                .field("name").field("age").field("email").type(MultiMatchQueryBuilder.Type.BEST_FIELDS)).build();
        SearchHits<CustomerES> customers = template.search(query, CustomerES.class, IndexCoordinates.of("customer"));
        List<CustomerES> customermatch = new ArrayList<CustomerES>();
        customers.forEach(customerSearchHit -> {
            customermatch.add(customerSearchHit.getContent());
        });

        return customermatch;
    }

    public OrderEventES updateOrder(String id){
        OrderEventES orderEvent =  orderEventRepo.findById(id).get();
        orderEvent.setStatus("SUCCESSFULL");
        orderEvent.setMessage("ORDER PLACED.......");
        return orderEventRepo.save(orderEvent);
    }
}
