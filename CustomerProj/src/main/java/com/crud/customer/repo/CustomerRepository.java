package com.crud.customer.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.crud.customer.model.Customer;

/**
 * Interface for performing CRUD operations with Customer records. All the basic operations are 
 * providing by {@link CrudRepository} interface provided by Spring MongoDb framework. That was the reason to use and empty interface. 
 * Spring Boot provides the configuration and framework for autowiring the implementation. 
 * 
 * @author <a href="mailto:oshailesh@gmail.com">Me</a>
 * @version 1.0
 * */
@RepositoryRestResource
public interface CustomerRepository extends MongoRepository<Customer, String>{

}
