package com.example.customerclient;

import java.util.Collection;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode;

@SpringBootTest(classes = CustomerClientApplication.class)
@AutoConfigureStubRunner(stubsMode = StubsMode.LOCAL,
    ids = {"com.example:customer-service:+:stubs:8082"})
class CustomerClientTest {

  @Autowired
  private CustomerClient client;

  @Test
  public void shouldReturnAllCustomers() throws Exception {
    Collection<Customer> customers = this.client.getAllCustomers();
    BDDAssertions.then(customers).size().isEqualTo(2);
    BDDAssertions.then(customers.iterator().next().getId()).isEqualTo(1L);
    BDDAssertions.then(customers.iterator().next().getName()).isEqualTo("Tommy");
    BDDAssertions.then(customers.iterator().next().getName().getClass().getSimpleName()).isEqualTo("String");
  }
}