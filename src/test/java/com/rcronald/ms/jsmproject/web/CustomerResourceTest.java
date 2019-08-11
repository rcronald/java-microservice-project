package com.rcronald.ms.jsmproject.web;

import com.rcronald.ms.jsmproject.Application;
import com.rcronald.ms.jsmproject.domain.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerResourceTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testCreateCustomerWithData() throws Exception {
        Customer customer1 = new Customer("Ronald", "Requena", 33,
                new SimpleDateFormat("yyyy-MM-dd").parse("1986-06-02"));
        HttpEntity<Customer> entity = new HttpEntity<>(customer1, headers);
        ResponseEntity<Customer> response = restTemplate.exchange(
                createURLWithPort("/api/v1/customer"), HttpMethod.POST, entity, Customer.class);

        int responseCode = response.getStatusCodeValue();
        Assert.assertEquals(201, responseCode);

        String location = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
        Assert.assertTrue(location.contains("/api/v1/customer"));

        Customer responseCustomer = response.getBody();
        Assert.assertTrue(responseCustomer.getName().contains("Ronald"));
        Assert.assertTrue(responseCustomer.getLastName().contains("Requena"));
    }

    @Test()
    public void testCreateCustomerWithOutData() {
        Customer customer1 = new Customer();
        HttpEntity<Customer> entity = new HttpEntity<>(customer1, headers);
        ResponseEntity<Customer> response = restTemplate.exchange(
                createURLWithPort("/api/v1/customer"), HttpMethod.POST, entity, Customer.class);

        int responseCode = response.getStatusCodeValue();
        Assert.assertEquals(400, responseCode);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }


}
