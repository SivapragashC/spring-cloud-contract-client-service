package com.example.customerclient;

import java.util.ArrayList;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


public class CustomerClient {

  private final  RestTemplate restTemplate;

  public CustomerClient(RestTemplate restTemplate) {
    this.restTemplate=restTemplate;
  }

  public Collection<Customer> getAllCustomers() {
    ParameterizedTypeReference<Collection<Customer>> ptr = new ParameterizedTypeReference<Collection<Customer>>() { };

    ResponseEntity<Collection<Customer>> responseEntity = this.restTemplate.exchange("http://localhost:8082/customers", HttpMethod.GET, null, ptr);
    return responseEntity.getBody();
  }
}
