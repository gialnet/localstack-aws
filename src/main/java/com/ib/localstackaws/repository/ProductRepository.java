package com.ib.localstackaws.repository;

import com.ib.localstackaws.SqsModel.Product;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@EnableScan
@Repository
public interface ProductRepository extends CrudRepository<Product, String> {
    List<Product> findByName(String name);
}
