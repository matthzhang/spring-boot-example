package com.amigoscode;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.amigoscode.CustomerRepository;
//import com.amigoscode.Customer;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {

    private final CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping()
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    record NewCustomerRequest(
        String name,
        String email,
        Integer age
    ) {

    }

    @PostMapping()
    public void addCustomer(@RequestBody NewCustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);
    }
    // Ex. Json body to send:
    // {
    //     "name": "Jamila",
    //     "email": "jamila@gmail.com",
    //     "age": 33
    // }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id) {
        customerRepository.deleteById(id);
    }
    // Ex. DELETE request to localhost:8080/api/v1/customers/1

    @PutMapping("{customerId}")
    public void updateCustomer(@PathVariable("customerId") Integer id, @RequestBody NewCustomerRequest request) {
        Customer customer = customerRepository.findById(id);
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);
    }

    // api url: http//localhost:8080/greet
    // @GetMapping("/greet")
    // public GreetResponse greet() {
    //     //returns json object: {"greet":"Hello"}
    //     GreetResponse response = new GreetResponse(
    //         "Hello",
    //         List.of("Java", "Golang", "Javascript"),
    //         new Person("Alex")
    //     );

    //     return response;
    // }

    // record Person(String name)
    // {

    // }

    // record GreetResponse(
    //     String greet, 
    //     List<String> favProgrammingLanguages,
    //     Person person
    // ) {

    // }
}
