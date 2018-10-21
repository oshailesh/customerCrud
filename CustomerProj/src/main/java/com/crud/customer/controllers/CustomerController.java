package com.crud.customer.controllers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.crud.customer.model.Customer;
import com.crud.customer.repo.CustomerRepository;

/**
 * REST API endpoints for providing CRUD functionality on {@link Customer} records.
 * This class will define the basic CRUD functionality for which in turn will be persisted in a database
 * using {@link CustomerRepository}. 
 * <p>
 * This class provides input validation using {@code javax.validation} API. 
 * This class also contains utility to manage the exceptions and collectively present to the user in a readable form.
 * All the database interactions invoked by these endpoints are done with the help of {@link CustomerRespository} interface.
 * With the assistance of SpringBoot we are able to invoke the concrete implementation of {@link CrudRepository} interface 
 * which is bundled in the {@code Spring MongoDb} plugin 
 * 
 * @author <a href="mailto:oshailesh@gmail.com">Me</a>
 * @version 1.0
 * */

@RestController
public class CustomerController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	CustomerRepository custRepo;
	
	/**
	 * This method is an endpoint for getting all the existing customer records in the database.
	 * @return List of Customer records
	 * */
	@RequestMapping(method =RequestMethod.GET ,value="/customers")
	public Iterable<Customer> customer(){
		logger.info("Got list request");
		
		return custRepo.findAll();
	}

	/**
	 * This method is an endpoint for saving a customer record in the database.
	 * @param cust {@link Customer} object
	 * @return {@link Customer} record
	 * */
	@RequestMapping(method =RequestMethod.POST ,value="/customers")
	public Customer save(@RequestBody @Valid Customer cust){
		logger.info("Got save request");
		
		custRepo.save(cust);
		logger.info("Saved Customer {}",cust);
		return cust;
	}
	
	/**
	 * This method is an endpoint for updating a customer record in the database.
	 * @param id String containing emailAddress
	 * @param cust {@link Customer} object
	 * @return {@link Customer} record
	 * */
	@RequestMapping(method =RequestMethod.PUT ,value="/customers/{id}")
	public Customer update(@PathVariable String id, @RequestBody @Valid Customer cust){
		logger.info("Got update request for {}",id);
		
		Optional<Customer> optCust =  custRepo.findById(id);
		try {
			Customer existing = optCust.get();
			if(existing!=null) {
				if(cust.getFirstName()!=null)
					existing.setFirstName(cust.getFirstName());
				if(cust.getLastName()!=null)
					existing.setLastName(cust.getLastName());
				if(cust.getAddress()!=null)
					existing.setAddress(cust.getAddress());
				
				custRepo.save(existing);
				logger.info("Updated Customer {}",cust);
			}
		}catch(NoSuchElementException ex) {
			logger.debug("No such Id present {}",ex);
		}
		catch(Exception ex) {
			logger.debug("Bad request",ex);
		}
		return cust;
	}
	
	/**
	 * This method is an endpoint for fetching a customer record in the database based on the {id}.
	 * In this case the {id} is the {@code emailAddress} of the {@link Customer}
	 * @param cust {@link Customer} object
	 * @return {@link Customer} record
	 * */
	@RequestMapping(method =RequestMethod.GET ,value="/customers/{id}")
	public Customer show(@PathVariable @Email String id){
		logger.info("Got find request for {}",id);
		Optional<Customer> cust = custRepo.findById(id);
		return cust.get();		
	}
	
	/**
	 * This method is an endpoint for deletng a customer record from the database based on the {id}.
	 * In this case the {id} is the {@code emailAddress} of the {@link Customer}
	 * @param id String which contains the emailAddress
	 * @return String
	 * */
	@RequestMapping(method = RequestMethod.DELETE, value = "/customers/{id}")
	public String delete(@PathVariable @Email String id) {
		logger.info("Got delete request for {}",id);
		Optional<Customer> optCust = custRepo.findById(id);

		Customer cust = optCust.get();
		if(cust!=null)
		custRepo.delete(cust);
		
		logger.info("Deleted Customer by email ID{}",id);
//TODO: Need to change this logic
		return "";
	}
	/**
	 * This method is responsible for Exception Handling and streaming the error messages in a collection. 
	 * @param ex A parameter of type MethodArgumentNotValidException
	 * @return List of String which contain the user friendly error messages
	 * */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
	    return ex.getBindingResult()
	        .getAllErrors().stream()
	        .map(ObjectError::getDefaultMessage)
	        .collect(Collectors.toList());
	}

}
